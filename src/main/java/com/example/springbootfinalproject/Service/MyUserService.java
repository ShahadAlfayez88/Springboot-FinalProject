package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.DTO.CustomerDTO;
import com.example.springbootfinalproject.DTO.ServiceProviderDTO;
import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MyUserService {

    private final MyUserRepository myUserRepository;
    private final CustomerRepository customerRepository;

    private final ServiceProviderRepository serviceProviderRepository;

    private final  ProviderService providerService;

    private final  CustomerService customerService;


    // Get All users
    public List<MyUser> getAllUsers(){
        return myUserRepository.findAll();
    }

    // Add Customer & User
    public void addCustomer(CustomerDTO customerDTO){

        MyUser currentUser = myUserRepository.findMyUsersByUsername(customerDTO.getUsername());

        // check username
        if(currentUser==null) {

        // add user
        MyUser myUser = new MyUser();
        myUser.setRole(customerDTO.getRole());
        myUser.setPassword(customerDTO.getPassword());
//        String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
//        myUser.setPassword(hashedPassword);
        myUser.setUsername(customerDTO.getUsername());

        // add customer
        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getEmail(),customerDTO.getPhoneNumber(),myUser,null,null,null);

        myUser.setCustomer(customer);
        myUserRepository.save(myUser);

        customer.setMyUser(myUser);
        customerRepository.save(customer);

        }
        else throw new ApiException("The User name is used by another user");

    }

    // Add Service Provider & User
    public void addServiceProvider(ServiceProviderDTO serviceProviderDTO){

        MyUser currentUser = myUserRepository.findMyUsersByUsername(serviceProviderDTO.getUsername());

        // check username
        if(currentUser==null) {

            //add user
            MyUser myUser = new MyUser();
            myUser.setRole(serviceProviderDTO.getRole());
            myUser.setPassword(serviceProviderDTO.getPassword());
//          String hashedPassword=new BCryptPasswordEncoder().encode(myUser.getPassword());
//          myUser.setPassword(hashedPassword);
            myUser.setUsername(serviceProviderDTO.getUsername());


            // add service provider
            ServiceProvider serviceProvider = new ServiceProvider(serviceProviderDTO.getId(),serviceProviderDTO.getName(),serviceProviderDTO.getEmail(),serviceProviderDTO.getPhoneNumber(),serviceProviderDTO.getSpecialisedAt(),serviceProviderDTO.getYearsOfExperience(),serviceProviderDTO.getCommercialRegister(),myUser,null,null,null,null);

            serviceProvider.setMyUser(myUser);
            serviceProviderRepository.save(serviceProvider);

            myUser.setServiceProvider(serviceProvider);
            myUserRepository.save(myUser);


        }
        else throw new ApiException("The User name is used by another user");
    }


    // Delete User
    public void deleteUser(Integer id){
        MyUser myUser=myUserRepository.findMyUsersById(id);
        if(myUser==null){
            throw new ApiException("User Not Found");
        }
        myUserRepository.delete(myUser);
    }



    // Update Customer and User
    public void updateCustomer(CustomerDTO customerDTO, Integer id){
        // NewUser
        MyUser myUser = new MyUser();

        myUser.setRole(customerDTO.getRole()); myUser.setPassword(customerDTO.getPassword());
        myUser.setId(id); myUser.setUsername(customerDTO.getUsername());

        myUserRepository.save(myUser);


        // new customer
        Customer customer = new Customer(customerDTO.getId(),customerDTO.getName(),customerDTO.getEmail(),customerDTO.getPhoneNumber(),myUser,null,null,null);
        customerService.updateCustomer(id,customer);
    }

    // Update Provider and User
    public void updateProvider(ServiceProviderDTO serviceProviderDTO, Integer id){
        // NewUser
        MyUser myUser = new MyUser();

        myUser.setRole(serviceProviderDTO.getRole()); myUser.setPassword(serviceProviderDTO.getPassword());
        myUser.setId(id); myUser.setUsername(serviceProviderDTO.getUsername());

        myUserRepository.save(myUser);


        // new provider
        ServiceProvider serviceProvider = new ServiceProvider(serviceProviderDTO.getId(), serviceProviderDTO.getName(), serviceProviderDTO.getEmail(), serviceProviderDTO.getPhoneNumber(), serviceProviderDTO.getSpecialisedAt(), serviceProviderDTO.getYearsOfExperience(), serviceProviderDTO.getCommercialRegister(),myUser,null,null,null,null);
        providerService.updateProvider(id,serviceProvider);
    }


    // Update User
    public void updateUser(MyUser newUser, Integer id){
        MyUser oldUser=myUserRepository.findMyUsersById(id);

        newUser.setId(id);
        newUser.setRole(oldUser.getRole());
//        newUser.setPassword(new BCryptPasswordEncoder().encode(newUser.getPassword()));

        myUserRepository.save(newUser);
    }
}
