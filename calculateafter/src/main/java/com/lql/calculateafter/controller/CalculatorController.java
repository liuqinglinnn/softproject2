package com.lql.calculateafter.controller;

import com.lql.calculateafter.bean.Calculator;
import com.lql.calculateafter.service.CalculatorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@Slf4j
public class CalculatorController {
    @Autowired
    private CalculatorService calculatorService;
    @PostMapping("/save")
    public void save(@RequestBody Calculator calculator){
        log.info("save success");
        calculatorService.save(calculator);
    }
    @GetMapping("/ans")
    public String ans(){
        String ans = calculatorService.ans();
        return ans;
    }
}
