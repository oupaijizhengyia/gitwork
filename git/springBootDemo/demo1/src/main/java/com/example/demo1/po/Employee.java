package com.example.demo1.po;

import com.example.demo1.util.DataUtil;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
@Data
public class Employee {
    private Integer id;
    @ApiModelProperty("员工姓名")
    private String employeeName;

    private String initialCode;

    private Boolean useState;
    @ApiModelProperty("工号")
    private String employeeCode;
    @ApiModelProperty("电话")
    private String employeeTel;

    private Integer roleId;
    private Integer isOperator;
    @ApiModelProperty("创建时间")
    private String createTime;
    @ApiModelProperty("修改时间")
    private String modTime;

    public void setCreateTime(Date createTime) {
        this.createTime = DataUtil.dataFormate(createTime);
    }

    public void setModTime(Date modTime) {
        this.modTime =  DataUtil.dataFormate(modTime);
    }
}