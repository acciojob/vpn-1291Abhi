package com.driver.model;

import javax.persistence.*;

@Entity

// Note: Do not write @Enumerated annotation above CountryName in this model.
public  class Country{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private CountryName countryName;
    private String code;

    public Country(CountryName countryName, String code) {
        this.countryName = countryName;
        this.code = code;
    }

    public Country() {
    }

    public CountryName getCountryName() {
        return countryName;
    }

    public void setCountryName(CountryName countryName) {
        this.countryName = countryName;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    @ManyToOne
    @JoinColumn
    ServiceProvider serviceProvider;

    @OneToOne
    @JoinColumn
    User user;

    public ServiceProvider getServiceProvider() {
        return serviceProvider;
    }

    public void setServiceProvider(ServiceProvider serviceProvider) {
        this.serviceProvider = serviceProvider;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}