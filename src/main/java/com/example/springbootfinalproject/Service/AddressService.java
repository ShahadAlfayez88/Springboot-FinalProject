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

    //get all Address
    public List<Address> getAllAddresses(){
        return addressRepository.findAll();
    }


    //get Address by id
    public Address getAddressById(Integer id){
       Address address=addressRepository.findAddressById(id);
        if (address==null){
            throw new ApiException("Address Not Found!");
        }
        return address;
    }


    //add Address
    public void addAddressToCustomer(Address address, Integer user_id){

        MyUser myUser = myUserRepository.findMyUsersById(user_id);
        Customer customer = customerRepository.findCustomerByMyUser(myUser);

        if(customer==null||myUser==null){
            throw new ApiException("Not Found!");
        }

        //assign address
        address.setCustomer(customer);
        addressRepository.save(address);

        // assign customer
        customer.getAddress().add(address);
        customerRepository.save(customer);

    }

    public void addAddressToProvider(Address address, Integer provider_id){
        ServiceProvider ServiceProvider = serviceProviderRepository.findServiceProviderById(provider_id);

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


        if(oldAddress==null || myUser==null){
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


        if(oldAddress==null || myUser==null){
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

    public void deleteAddress(Integer id, Integer user_id){

       Address address=addressRepository.findAddressById(id);
       MyUser myUser = myUserRepository.findMyUsersById(user_id);


        if(address==null || myUser==null){
            throw new ApiException("address or user Not Found!");
        }else if(address.getServiceProvider().getMyUser().getId()!=user_id || address.getCustomer().getMyUser().getId()!=user_id ){
            throw new ApiException("Sorry , You do not have the authority to delete this address!");
        }

        addressRepository.delete(address);
    }
}
