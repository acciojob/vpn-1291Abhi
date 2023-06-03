package com.driver.services.impl;

import com.driver.Exception.CountryNotFoundException;
import com.driver.model.Admin;
import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.repository.AdminRepository;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.services.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    AdminRepository adminRepository1;

    @Autowired
    ServiceProviderRepository serviceProviderRepository1;

    @Autowired
    CountryRepository countryRepository1;

    @Override
    public Admin register(String username, String password) {
        Admin admin=new Admin();
        admin.setUsername(username);
        admin.setPassword(password);

        Admin savedAdmin=adminRepository1.save(admin);
        return savedAdmin;
    }

    @Override
    public Admin addServiceProvider(int adminId, String providerName) {
        Optional<Admin> admin=adminRepository1.findById(adminId);
        if(admin.isPresent()){
            List<ServiceProvider> serviceProviderList=admin.get().getServiceProviders();
            ServiceProvider serviceProvider=new ServiceProvider();
            serviceProvider.setName(providerName);
            serviceProvider.setAdmin(admin.get());
            serviceProviderRepository1.save(serviceProvider);

            serviceProviderList.add(serviceProvider);
            admin.get().setServiceProviders(serviceProviderList);
            return admin.get();
        }
        return null;
    }

    @Override
    public ServiceProvider addCountry(int serviceProviderId, String countryName) throws Exception{
            Country country=new Country();
            String cname=countryName.toUpperCase();
            if(cname=="IND" || cname=="AUS" || cname=="USA" || cname=="CHI" || cname=="JPN"){
                country.setCountryName(CountryName.valueOf(cname));
                Optional<ServiceProvider> serviceProvider=serviceProviderRepository1.findById(serviceProviderId);
                if(serviceProvider.isPresent()){
                    List<Country> countries=serviceProvider.get().getCountryList();
                    countries.add(country);
                    serviceProvider.get().setCountryList(countries);
                    return serviceProvider.get();
                }
                return null;
            }
            else{
                throw new CountryNotFoundException("Country not found");
            }

    }
}
