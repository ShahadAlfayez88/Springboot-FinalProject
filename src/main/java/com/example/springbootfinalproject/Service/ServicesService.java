package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;

import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepository serviceRepository;
//get all service
    public List<Services> getAllServices(){
        return serviceRepository.findAll();
    }
//get Services by id
    public Services getServicesById(Integer id){
        Services services=serviceRepository.findServicesById(id);
        if (services==null){
            throw new ApiException("Services Not Found!");
        }
        return services;
    }
    //add Services
    public void addServices(Services services){
        serviceRepository.save(services);
    }

    //update Services
       public void updateServices( Services services,Integer id){
        Services oldServices=serviceRepository.findServicesById(id);

           if(oldServices==null){
               throw new ApiException("Services Not Found");
           }

        oldServices.setId(id);
        oldServices.setName(services.getName());
        oldServices.setDescription(services.getDescription());
        oldServices.setCategory(services.getCategory());
        oldServices.setPrice(services.getPrice());
        oldServices.setFollowingPeriod(services.getFollowingPeriod());

        serviceRepository.save(oldServices);
    }
    //delete Services

    public void deleteServices(Integer id){
        Services services=serviceRepository.findServicesById(id);
        if(services==null){
            throw new ApiException("Services Not Found");
        }
        serviceRepository.delete(services);
    }


}
