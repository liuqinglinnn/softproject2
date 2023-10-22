package com.lql.calculateafter.controller;

import com.lql.calculateafter.bean.Loan;
import com.lql.calculateafter.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
public class LoanController {
    @Autowired
    private LoanService loanService;
    @PostMapping("/loan_int")
    public Double get_interest(@RequestBody Loan loan){
        Double date = loan.getLoan_date()*12;
        Double money = loan.getLoan_money();
        Double interest = null;
        List<Double> rate = loanService.rate();
        if(date <= 6){
            interest = money * rate.get(0)/100;
        } else if (date <= 12) {
            interest = money * rate.get(1)/100;
        } else if (date <= 36) {
            interest = money * rate.get(2)/100;
        } else if (date <= 60) {
            interest = money * rate.get(3)/100;
        }else {
            interest = money * rate.get(4)/100;
        }
        return interest;
    }
}
