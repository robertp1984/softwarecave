package com.example.springrestchart;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sales {
    private Country country;
    private List<Double> values;

    public Sales(Country country, List<Double> values) {
        this.country = country;
        this.values = new ArrayList<>(values);
    }

    public Country getCountry() {
        return country;
    }
    
    public List<Double> getValues() {
        return Collections.unmodifiableList(values);
    }
}
