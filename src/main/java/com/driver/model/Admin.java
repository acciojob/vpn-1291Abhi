package com.driver.model;

import net.bytebuddy.implementation.bind.annotation.Default;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity

public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String userName;
    private String password;

    public Admin(){}

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Admin(String userName, String password) {

        this.userName = userName;
        this.password = password;
    }

    public List<ServiceProvider> getServiceProviderList() {
        return serviceProviderList;
    }

    public void setServiceProviderList(List<ServiceProvider> serviceProviderList) {
        this.serviceProviderList = serviceProviderList;
    }

    @OneToMany(mappedBy = "admin", cascade = CascadeType.ALL)
    List<ServiceProvider> serviceProviderList=new ArrayList<>();

}
