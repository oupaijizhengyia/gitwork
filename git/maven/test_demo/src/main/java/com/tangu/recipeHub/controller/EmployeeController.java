package com.tangu.recipeHub.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName: EmployeeController
 * Author: yeyang
 * Date: 2018/8/8 16:11
 */
@Slf4j
@RestController
@ApiModel(value = "员工")
@RequestMapping("area")
public class EmployeeController {

    @ApiOperation(tags = "员工", value = "列出煎煮区域")
    @RequestMapping(value = "getDetail",method = RequestMethod.POST)
    public void getDetail(){

    }
}
