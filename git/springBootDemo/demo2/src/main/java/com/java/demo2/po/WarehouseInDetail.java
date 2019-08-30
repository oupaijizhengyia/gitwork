package com.java.demo2.po;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class WarehouseInDetail {
    private Integer id;

    private Integer warehouseInId;

    private Integer medicineId;

    private Integer standardId;

    private BigDecimal quantity;

    private BigDecimal price;

    private BigDecimal money;

    private String batchNumber;

    private String makeDate;

    private String validDate;

    private String quality;

    private Integer producerId;

    private String approvalNumber;

    private String remark;

}