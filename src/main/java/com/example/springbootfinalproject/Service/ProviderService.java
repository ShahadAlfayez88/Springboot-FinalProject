package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ProviderService {

    private final ServiceProviderRepository serviceProviderRepository;


    // Get - display
    public List<ServiceProvider> getProvider() {
        return serviceProviderRepository.findAll();
    }

    // Update
    public void updateProvider(Integer id, ServiceProvider serviceProvider) {
        ServiceProvider oldProvider = serviceProviderRepository.findServiceProviderById(id);
        if (oldProvider == null) {
            throw new ApiException("Id not found!!");
        }
        serviceProvider.setId(id);
        serviceProviderRepository.save(serviceProvider);
    }

    // Delete
    public void deleteProvider(Integer id) {
        ServiceProvider oldProvider = serviceProviderRepository.findServiceProviderById(id);
        if (oldProvider == null) {
            throw new ApiException("Id not found!!");
        }
        serviceProviderRepository.delete(oldProvider);
//        myUserRepository.delete();
    }


}
