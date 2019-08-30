package com.tangu.tangucore.security.controller;

import com.alibaba.fastjson.JSONObject;
import com.tangu.common.exception.TanguException;
import com.tangu.common.util.entity.ResponseModel;
import com.tangu.tangucore.tcmts2.util.Constants;
import com.tangu.tangucore.datasource.DynamicDataSourceTenantLocal;
import com.tangu.tangucore.security.exception.PermissionDeniedException;
import com.tangu.tangucore.security.jwt.JwtAuthenticationRequest;
import com.tangu.tangucore.security.jwt.JwtService;
import com.tangu.tangucore.security.jwt.JwtTokenUtil;
import com.tangu.tangucore.security.jwt.JwtUser;
import com.tangu.tangucore.tcmts2.po.TanguUser;
import com.tangu.tangucore.tcmts2.service.TanguUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fenglei on 8/29/17.
 */
@Api(value = "/auth", description = "通用登录接口")
@RestController
public class AuthenticationRestController  {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${tangu.datasource.sysadmin}")
    private String sysadmin;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private TanguUserService employeeService;

    private String PERMISSION_DENIED = "无权限登陆此系统";

    @ApiOperation(value = "用户登录", notes = "根据输入的tenant,userCode,password返回对应的token")
    @ApiImplicitParams({
            @ApiImplicitParam(value = "createAuthenticationToken",dataType = "JwtAuthenticationRequest")
    })
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.POST)
    public Object createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest) throws AuthenticationException, PermissionDeniedException {
      if(StringUtils.isBlank(authenticationRequest.getTenant()) 
          || StringUtils.isBlank(authenticationRequest.getUsername())
          || StringUtils.isBlank(authenticationRequest.getPassword())){
        throw new TanguException(Constants.USER_NOT_COMPLETE);
      }
      try {
            //判断是否账套已激活
            if(!DynamicDataSourceTenantLocal.containsDataSource(authenticationRequest.getTenant())){
                return new ResponseModel().attr(ResponseModel.KEY_ERROR, Constants.USER_NOT_FOUND);
            }
            //分配帐套
            if(sysadmin != null && authenticationRequest.getTenant() != null){
                DynamicDataSourceTenantLocal.setDataSourceName(authenticationRequest.getTenant());
            }
            final JwtUser userDetails = (JwtUser)jwtService.auth(authenticationRequest.getTenant(),authenticationRequest.getUsername(),authenticationRequest.getPassword());
            final String token = jwtTokenUtil.generateToken(userDetails,"web");
             Object obj =employeeService.login(authenticationRequest.getUsername(),authenticationRequest.getPassword());
            final TanguUser employee = JSONObject.parseObject(JSONObject.toJSONString(obj),TanguUser.class);
            return new ResponseModel().attr("token", ResponseEntity.ok(token)).attr("userId",employee.getId());
        }catch (UsernameNotFoundException e){
            throw new TanguException(Constants.USER_NOT_FOUND);
        }catch (PermissionDeniedException e){
            throw new TanguException(PERMISSION_DENIED);
        }
    }

    
    /**
    * @return
    * @throws AuthenticationException
    * @throws PermissionDeniedException
    */
    @RequestMapping(value = "${jwt.route.authentication.path}", method = RequestMethod.GET)
    public Object authDetect() throws AuthenticationException, PermissionDeniedException {
      return "success";
    }
}
