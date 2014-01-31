package com.example.ajaxevents;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

@Named
@SessionScoped
public class ValueCalculator implements Serializable {

    private static final long serialVersionUID = 23487237489324L;
    private Double pi = null;

    public String calculatePi() throws InterruptedException {
        double sum = 0;
        double mul = 1;
        for (int i = 1; i < 100000000; i++) {
            sum += mul / (2 * i - 1);
            mul = -mul;
        }
        
        pi = 4 * sum;
        return null;
    }

    public Double getPiValue() {
        return pi != null ? pi : 0;
    }

    public boolean getHasPiValue() {
        return pi != null;
    }
}
