package com.lql.calculateafter.controller;

import com.lql.calculateafter.bean.Deposit;
import com.lql.calculateafter.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class DepositController {
    @Autowired
    private DepositService depositService;
    @PostMapping("/dep_int")
    public Double get_interest(@RequestBody Deposit deposit){
        Double date = deposit.getDep_date()*12;
        Double money = deposit.getDep_money();
        List<Double> rate = depositService.rate();
        Double interest = null;
        if(date <= 3){
            interest = money * rate.get(0)/100;
        } else if (date <= 6) {
            interest = money * rate.get(1)/100;
        } else if (date <= 12) {
            interest = money * rate.get(2)/100;
        } else if (date <= 24) {
            interest = money * rate.get(3)/100;
        } else if (date <= 36) {
            interest = money * rate.get(4)/100;
        }else {
            interest = money * rate.get(5)/100;
        }
        return interest;
    }
}
