package com.lql.calculateafter.service.Impl;

import com.lql.calculateafter.bean.Calculator;
import com.lql.calculateafter.mapper.CalculatorMapper;
import com.lql.calculateafter.service.CalculatorService;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CalculatorServiceImpl implements CalculatorService {
    @Autowired
    private CalculatorMapper calculatorMapper;
    @Override
    public void save(Calculator calculator) {
        calculatorMapper.save(calculator);
    }

    @Override
    public String ans() {
        String ans = calculatorMapper.ans();
        return ans;
    }
}
