package com.lql.calculateafter.service.Impl;

import com.lql.calculateafter.mapper.DepositMapper;
import com.lql.calculateafter.service.DepositService;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepositServiceImpl implements DepositService {
    @Autowired
    private DepositMapper depositMapper;
    @Override
    public List<Double> rate() {
        List<Double> rate = depositMapper.rate();
        return rate;
    }
}
