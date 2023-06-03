package com.driver.services.impl;

import com.driver.model.Country;
import com.driver.model.CountryName;
import com.driver.model.ServiceProvider;
import com.driver.model.User;
import com.driver.repository.CountryRepository;
import com.driver.repository.ServiceProviderRepository;
import com.driver.repository.UserRepository;
import com.driver.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository3;
    @Autowired
    ServiceProviderRepository serviceProviderRepository3;
    @Autowired
    CountryRepository countryRepository3;

    @Override
    public User register(String username, String password, String countryName) throws Exception{
        User user=new User();
        user.setUsername(username);
        user.setPassword(password);
        Country country=new Country();
        country.setCountryName(CountryName.valueOf(countryName));

        user.setOriginalCountry(country);
        user.setConnected(false);
        return userRepository3.save(user);
    }

    @Override
    public User subscribe(Integer userId, Integer serviceProviderId) {
        Optional<User> user=userRepository3.findById(userId);
        Optional<ServiceProvider> serviceProvider=serviceProviderRepository3.findById(serviceProviderId);
        if(!user.isPresent() && !serviceProvider.isPresent())
            return null;
        List<User> userList=serviceProvider.get().getUsers();
        List<ServiceProvider>serviceProviderList=user.get().getServiceProviderList();
        userList.add(user.get());
        serviceProviderList.add(serviceProvider.get());
        user.get().setServiceProviderList(serviceProviderList);
        serviceProvider.get().setUsers(userList);
        return user.get();
    }
}
