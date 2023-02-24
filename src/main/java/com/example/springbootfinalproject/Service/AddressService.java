package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.AddressRepository;
import com.example.springbootfinalproject.Repository.CustomerRepository;
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
    public void addAddressToCustomer(Address address, Integer customer_id){
        Customer customer = customerRepository.findCustomerById(customer_id);

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

    //update Address
    public void updateAddress( Address address,Integer id){
        Address oldAddress=addressRepository.findAddressById(id);
        if(oldAddress==null){
            throw new ApiException("Address Not Found");
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

    public void deleteAddress(Integer id){
       Address address=addressRepository.findAddressById(id);
        if(address==null){
            throw new ApiException("Address Not Found");
        }
        addressRepository.delete(address);
    }
}
