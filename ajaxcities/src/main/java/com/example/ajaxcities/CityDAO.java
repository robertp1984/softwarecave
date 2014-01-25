package com.example.ajaxcities;

import java.util.HashMap;
import java.util.Map;
import javax.ejb.Stateless;

@Stateless
public class CityDAO {
    
    private static String[] ALL_COUNTRIES = { "France", "Germany", "Poland", "USA"};
    private static Map<String, String[]> CITIES_MAP = createCitiesMap();
    
    public String[] getAllCountries() {
        return ALL_COUNTRIES;
    }
    
    public String[] getCities(String country) {
        return CITIES_MAP.get(country);
    }
    
    private static Map<String, String[]> createCitiesMap() {
        HashMap<String, String[]> result = new HashMap<>();
        result.put("France", new String[] {"Paris", "Avignon", "Bordeaux"});
        result.put("Germany", new String[] {"Berlin", "Munich", "Hamburg"});
        result.put("Poland", new String[] {"Warsaw", "Cracow"});
        result.put("USA", new String[] {"New York", "Los Angeles", "Chicago"});
        
        return result;
    }
}
