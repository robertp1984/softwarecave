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
        Thread.sleep(2000);
        pi = Math.PI;
        return null;
    }
    
    public Double getPiValue() {
        return pi != null ? pi : 0;
    }
    
    public boolean getHasPiValue() {
        return pi != null;
    }
}
