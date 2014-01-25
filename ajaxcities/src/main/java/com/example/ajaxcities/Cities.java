package com.example.ajaxcities;

import java.io.Serializable;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.model.SelectItem;
import javax.inject.Named;

@Named
@SessionScoped
public class Cities implements Serializable {
    private static final long serialVersionUID = 2347238972389L;
    private static final Logger LOGGER = Logger.getLogger(Cities.class.getName());

    @EJB
    private CityDAO dao;
    
    private SelectItem[] countrySelectItems;
    private String country;
    
    private SelectItem[] citySelectItems;
    private String city;

    public Cities() {
        dao = new CityDAO();
        createCountrySelectItems();
        createCitySelectItems();
    }
    
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        createCitySelectItems();
        LOGGER.info(String.format("Selected country: %s", country));
    }

    public SelectItem[] getAllCountries() {
        return countrySelectItems;
    }
    
    private void createCountrySelectItems() {
        String[] countries = dao.getAllCountries();
        country = countries[0];
        countrySelectItems = new SelectItem[countries.length];
        for (int i = 0; i < countries.length; i++)
            countrySelectItems[i] = new SelectItem(countries[i], countries[i]);
    }
    
    public String getCity() {
        return city;
    }
    
    public void setCity(String city) {
        this.city = city;
    }

    private void createCitySelectItems() {
        String[] cities = dao.getCities(country);
        if (cities != null && cities.length > 0) {
            city = cities[0];
            citySelectItems = new SelectItem[cities.length];
            for (int i = 0; i < cities.length; i++) {
                LOGGER.info(String.format("Adding to cities: %s", cities[i]));
                citySelectItems[i] = new SelectItem(cities[i], cities[i]);
            }
        } else {
            city = "";
            citySelectItems = new SelectItem[0];
        }
    }
    
    public SelectItem[] getAllCities() {
        return citySelectItems;
    }
}
