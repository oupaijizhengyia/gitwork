package com.tangu.tcmts.dao;

import com.tangu.tcmts.po.WarehouseMedicine;

public interface WarehouseMedicineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseMedicine record);

    int insertSelective(WarehouseMedicine record);

    WarehouseMedicine selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarehouseMedicine record);

    int updateByPrimaryKey(WarehouseMedicine record);
}