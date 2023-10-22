package com.lql.calculateafter.mapper;

import com.lql.calculateafter.bean.Calculator;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface CalculatorMapper {
    @Insert("insert into cal.calculator (equation,result) values (#{equation},#{result})")
    void save(Calculator calculator);
    @Select("SELECT result FROM cal.calculator ORDER BY id DESC LIMIT 1")
    String ans();
}
