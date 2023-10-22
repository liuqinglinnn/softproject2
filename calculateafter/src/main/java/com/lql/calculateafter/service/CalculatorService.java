package com.lql.calculateafter.service;

import com.lql.calculateafter.bean.Calculator;

public interface CalculatorService {
    void save(Calculator calculator);
    String ans();
}
