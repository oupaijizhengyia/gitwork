package com.java.demo2.controller;

import com.java.demo2.dao.WarehouseInDetailMapper;
import com.java.demo2.po.WarehouseInDetail;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

/**
 * FileName: WarehouseController
 * Author: yeyang
 * Date: 2018/3/29 10:34
 */
@RestController
@Api(value = "详情管理",description = "no comment")
@RequestMapping("/warehouse")
@Slf4j
public class WarehouseController {

    @Autowired
    WarehouseInDetailMapper warehouseInDetailMapper;

    @ApiOperation(value = "查询所有的详细",tags = "详情管理")
    @RequestMapping(value = "listAll", method = RequestMethod.POST)
    public Object listAll(){
        HashMap<String,Object> re = new HashMap<>(2);
        List<WarehouseInDetail> warehouseInDetails = warehouseInDetailMapper.getAll();
        re.put("data",warehouseInDetails);
        return re;
    }





}
