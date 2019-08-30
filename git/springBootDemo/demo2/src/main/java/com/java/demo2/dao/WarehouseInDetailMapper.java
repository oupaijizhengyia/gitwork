package com.java.demo2.dao;

import com.java.demo2.po.WarehouseInDetail;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface WarehouseInDetailMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(WarehouseInDetail record);

    int insertSelective(WarehouseInDetail record);

    WarehouseInDetail selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(WarehouseInDetail record);

    int updateByPrimaryKey(WarehouseInDetail record);
    @Select("select * from warehouse_in_detail")
    List<WarehouseInDetail> getAll();
}