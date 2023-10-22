package com.lql.calculateafter.service.Impl;

import com.lql.calculateafter.mapper.LoanMapper;
import com.lql.calculateafter.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoanServiceImpl implements LoanService {
    @Autowired
    private LoanMapper loanMapper;

    @Override
    public List<Double> rate() {
        List<Double> rate = loanMapper.rate();
        return rate;
    }
}
