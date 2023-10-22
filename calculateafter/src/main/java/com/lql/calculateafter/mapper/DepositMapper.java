package com.lql.calculateafter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface DepositMapper {
    @Select("select dep_rate from cal.deposit")
    List<Double> rate();
}
