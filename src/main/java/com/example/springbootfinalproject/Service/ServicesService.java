package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;

import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import com.example.springbootfinalproject.Repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ServicesService {
    private final ServicesRepository serviceRepository;
    private final ServiceProviderRepository serviceProviderRepository;
    private final MyUserRepository myUserRepository;



    //get all service
    public List<Services> getAllServices(){
        return serviceRepository.findAll();
    }
    //get all service by user id
    public List<Services> getAllServicesById(Integer user_id){

        MyUser myUser =myUserRepository.findMyUsersById(user_id);
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByMyUser_Id(user_id);

        if(serviceProvider==null||myUser==null){
            throw new ApiException("Not Found!");
        }

        return serviceProvider.getServices();
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
    public void addServices(Services services,  Integer user_id){
        MyUser myUser = myUserRepository.findMyUsersById(user_id);
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByMyUser_Id(user_id);

        if(serviceProvider==null||myUser==null){
            throw new ApiException("Not Found!");
        }

        //assign service
        services.setServiceProvider(serviceProvider);
        serviceRepository.save(services);
        // assign provider
        serviceProvider.getServices().add(services);
        serviceProviderRepository.save(serviceProvider);


    }

    //update Services
       public void updateServices(Services services ,Integer user_id , Integer id){

        MyUser myUser = myUserRepository.findMyUsersById(user_id);
        Services oldServices=serviceRepository.findServicesById(id);

        // check user and provider

           if(oldServices==null || myUser==null){
               throw new ApiException("service or user Not Found!");
           }else if(oldServices.getServiceProvider().getMyUser().getId()!=user_id){
               throw new ApiException("Sorry , You do not have the authority to update this Service!");
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

    public void deleteServices(Integer id, Integer user_id){
        MyUser myUser = myUserRepository.findMyUsersById(user_id);
        Services services=serviceRepository.findServicesById(id);


        // check user and provider

        if(services==null || myUser==null){
            throw new ApiException("service or user Not Found!");
        }else if(services.getServiceProvider().getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to delete this Service!");
        }

        serviceRepository.delete(services);
    }

    // get services by category

    public List getByCategory(String category, Integer user_id){
        MyUser myUser = myUserRepository.findMyUsersById(user_id);
        List<Services> services=serviceRepository.findAllByCategory(category);


        // check user and services

        if(services==null || myUser==null){
            throw new ApiException("service or user Not Found!");
        }

        return services;
    }

    // get service by name

    public Services getByName(String name, Integer user_id){
        MyUser myUser = myUserRepository.findMyUsersById(user_id);
        Services services=serviceRepository.findServicesByName(name);


        // check user and services

        if(services==null || myUser==null){
            throw new ApiException("service or user Not Found!");
        }

        return services;
    }


}
