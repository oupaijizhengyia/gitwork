package com.tangu.tcmts.po;

import java.math.BigDecimal;
import java.util.Date;

public class WarehouseMedicine {
    private Integer id;

    private Integer warehouseId;

    private Integer medicineId;

    private Integer standardId;

    private String storageLocation;

    private BigDecimal upLimit;

    private BigDecimal lowerLimit;

    private Date createTime;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(Integer warehouseId) {
        this.warehouseId = warehouseId;
    }

    public Integer getMedicineId() {
        return medicineId;
    }

    public void setMedicineId(Integer medicineId) {
        this.medicineId = medicineId;
    }

    public Integer getStandardId() {
        return standardId;
    }

    public void setStandardId(Integer standardId) {
        this.standardId = standardId;
    }

    public String getStorageLocation() {
        return storageLocation;
    }

    public void setStorageLocation(String storageLocation) {
        this.storageLocation = storageLocation == null ? null : storageLocation.trim();
    }

    public BigDecimal getUpLimit() {
        return upLimit;
    }

    public void setUpLimit(BigDecimal upLimit) {
        this.upLimit = upLimit;
    }

    public BigDecimal getLowerLimit() {
        return lowerLimit;
    }

    public void setLowerLimit(BigDecimal lowerLimit) {
        this.lowerLimit = lowerLimit;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}