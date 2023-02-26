package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.AddressRepository;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {
    private final AddressRepository addressRepository;

    private final CustomerRepository customerRepository;

    private final ServiceProviderRepository serviceProviderRepository;

    private final MyUserRepository myUserRepository;

    //get all Address - customer
    public List<Address> getAllAddresses(Integer user_id){
      return   addressRepository.findAll();
    }

    public List<Address> getCustomerAddresses(Integer userId) {
        MyUser myUser = myUserRepository.findMyUsersById(userId);
        Customer customer = customerRepository.findCustomerByMyUser_Id(userId);
        if(myUser==null || customer==null){
            throw new ApiException("User Not Found!");
        }
        return myUser.getServiceProvider().getAddress();
    }
    public List<Address> getProviderAddresses(Integer userId) {
        MyUser myUser = myUserRepository.findMyUsersById(userId);
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByMyUser_Id(userId);
        if(myUser==null || serviceProvider==null){
            throw new ApiException("User Not Found!");
        }
        return myUser.getServiceProvider().getAddress();
    }


    //get Address by id
    public Address getAddressById(Integer user_id){
       Address address=addressRepository.findAddressById(user_id);
        if (address==null){
            throw new ApiException("Address Not Found!");
        }
        return address;
    }


    //add Address
    public void addAddressToCustomer(Address address, Integer userId){

        MyUser myUser = myUserRepository.findMyUsersById(userId);
        Customer customer = customerRepository.findCustomerByMyUser_Id(userId);

        if(customer==null||myUser==null||customer.getMyUser()==null){
            throw new ApiException("Not Found!");
        }else if(customer.getMyUser().getId()!=userId){
            throw new ApiException("Sorry , You do not have the authority to add this address!");
        }

        //assign address
        address.setCustomer(customer);
        addressRepository.save(address);

        // assign customer
        customer.getAddress().add(address);
        customerRepository.save(customer);

    }

    public void addAddressToProvider(Address address,  Integer userId){
        ServiceProvider ServiceProvider = serviceProviderRepository.findServiceProviderByMyUser_Id(userId);
        MyUser myUser = myUserRepository.findMyUsersById(userId);

        if(ServiceProvider==null||myUser==null||ServiceProvider.getMyUser()==null){
            throw new ApiException("Not Found!");
        }else if(ServiceProvider.getMyUser().getId()!=userId){
            throw new ApiException("Sorry , You do not have the authority to add this address!");
        }

        //assign address
        address.setServiceProvider(ServiceProvider);
        addressRepository.save(address);

        // assign customer
        ServiceProvider.getAddress().add(address);
        serviceProviderRepository.save(ServiceProvider);

    }

    //update provider Address
    public void updateProviderAddress(Address address, Integer user_id, Integer id){

        MyUser myUser = myUserRepository.findMyUsersById(user_id);

        Address oldAddress=addressRepository.findAddressById(id);


        if(oldAddress==null || myUser==null || oldAddress.getServiceProvider().getMyUser()==null){
            throw new ApiException("address or user Not Found!");
        }else if(oldAddress.getServiceProvider().getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to update this address!");
        }


        oldAddress.setId(id);
        oldAddress.setStreet(address.getStreet());
        oldAddress.setCity(address.getCity());
        oldAddress.setNeighborhood(address.getNeighborhood());
        oldAddress.setPostCode(address.getPostCode());
        oldAddress.setCountry(address.getCountry());

        addressRepository.save(oldAddress);
    }

    //update customer Address
    public void updateCustomerAddress( Address address, Integer user_id, Integer id){

        MyUser myUser = myUserRepository.findMyUsersById(user_id);

        Address oldAddress=addressRepository.findAddressById(id);


        if(oldAddress==null || myUser==null || oldAddress.getCustomer().getMyUser()==null){
            throw new ApiException("address or user Not Found!");
        }else if(oldAddress.getCustomer().getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to update this address!");
        }

        oldAddress.setId(id);
        oldAddress.setStreet(address.getStreet());
        oldAddress.setCity(address.getCity());
        oldAddress.setNeighborhood(address.getNeighborhood());
        oldAddress.setPostCode(address.getPostCode());
        oldAddress.setCountry(address.getCountry());

        addressRepository.save(oldAddress);
    }


    //delete Address

    public void deleteCustomerAddress(Integer id, Integer user_id){

       Address address=addressRepository.findAddressById(id);
       MyUser myUser = myUserRepository.findMyUsersById(user_id);


        if(address==null || myUser==null){
            throw new ApiException("address or user Not Found!");
        }else if(address.getServiceProvider().getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to delete this address!");
        }

        addressRepository.delete(address);
    }
    public void deleteProviderAddress(Integer id, Integer user_id){

        Address address=addressRepository.findAddressById(id);
        MyUser myUser = myUserRepository.findMyUsersById(user_id);


        if(address==null || myUser==null){
            throw new ApiException("address or user Not Found!");
        }else if(address.getServiceProvider().getMyUser().getId()!=user_id){
            throw new ApiException("Sorry , You do not have the authority to delete this address!");
        }

        addressRepository.delete(address);
    }



}
