package com.example.springrestchart;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class SalesProvider {

    private static final List<Country> COUNTRIES = Arrays.asList(
            new Country("UK", "United Kingdom"),
            new Country("DE", "Germany"),
            new Country("FR", "France"));

    private Map<String, Sales> sales;

    @PostConstruct
    protected void init() {
        sales = createSales();
    }

    public List<Country> getCountries() {
        return Collections.unmodifiableList(COUNTRIES);
    }

    public Sales getSales(String countryCode) {
        Sales result = sales.get(countryCode);
        if (result != null) {
            return result;
        } else {
            throw new CountryNotFoundException("Country not found: " + countryCode);
        }
    }

    private static Map<String, Sales> createSales() {
        Calendar calendar = GregorianCalendar.getInstance();
        int days = calendar.get(GregorianCalendar.DAY_OF_MONTH);

        Map<String, Sales> result = new LinkedHashMap<>();
        for (Country country : COUNTRIES) {
            result.put(country.getCode(), createRandomValues(country, days));
        }
        return result;
    }

    private static Sales createRandomValues(Country country, int count) {
        List<Double> values = new ArrayList<>();

        double lastValue = Math.random() * 100;
        values.add(lastValue);
        for (int i = 1; i < count; i++) {
            lastValue += (Math.random() - 0.5);
            values.add(lastValue);
        }
        return new Sales(country, values);
    }

}
