package com.tangu.tangucore.tcmts2.po;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TanguUser {
    private Integer id;
    private String employeeName;
    private String employeeCode;
    private Integer roleId;
    private String employeePassword;
    @ApiModelProperty(value = "是否为操作员 1.是 0.否")
    private Integer isOperator;
}