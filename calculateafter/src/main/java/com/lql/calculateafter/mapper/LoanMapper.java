package com.lql.calculateafter.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LoanMapper {
    @Select("select loan_rate from cal.loan")
    List<Double> rate();
}
