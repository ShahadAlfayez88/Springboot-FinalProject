package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProviderService {

    private final ServiceProviderRepository serviceProviderRepository;

    private final MyUserRepository myUserRepository;
    // Get - display
    public List<ServiceProvider> getProvider() {
        return serviceProviderRepository.findAll();
    }

    // Update
    public void updateProvider(Integer user_id, ServiceProvider serviceProvider) {
        ServiceProvider oldProvider = serviceProviderRepository.findServiceProviderByMyUser_Id(user_id);
        if (oldProvider == null) {
            throw new ApiException("Id not found!!");
        }
        serviceProvider.setId(oldProvider.getId());
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


    // get order by id
    public Object getOrderByID(Integer provider_id, Integer order_id) {

        ServiceProvider provider = serviceProviderRepository.findServiceProviderById(provider_id);

        List<Object> details = new ArrayList<>();


        for(int i = 0 ; i<provider.getBookingServices().size();i++){

            if(provider.getBookingServices().get(i).getId()==order_id){
                OrderDetail orderDetail = new OrderDetail( provider.getBookingServices().get(i).getCustomer().getName(),provider.getBookingServices().get(i).getCustomer().getPhoneNumber(),provider.getBookingServices().get(i).getCustomer().getAddress());
                details.add(provider.getBookingServices().get(i));
                details.add(orderDetail);
                return details;
            }
        }

        throw new ApiException("Order not found");
    }

    //get all orders by id
    public Object getAllBookingServiceById(Integer userId) {
        MyUser myUser = myUserRepository.findMyUsersById(userId);
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByMyUser(myUser);
        if (myUser==null || serviceProvider == null) {
            throw new ApiException("Not Found!");
        }

        return serviceProvider.getBookingServices();
    }


}
