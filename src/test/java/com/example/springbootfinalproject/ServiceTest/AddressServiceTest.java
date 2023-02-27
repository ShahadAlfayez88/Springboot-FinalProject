package com.example.springbootfinalproject.ServiceTest;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.AddressRepository;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import com.example.springbootfinalproject.Service.AddressService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressServiceTest {
    // اوصل للservice

    @InjectMocks
    AddressService addressService;
    @Mock
    AddressRepository addressRepository;

    @Mock
    MyUserRepository myUserRepository;

    @Mock
    CustomerRepository customerRepository;
    @Mock
    ServiceProviderRepository serviceProviderRepository;

    MyUser myUser, myUser2;

    Address address1, address2, address3,address4;

    List<Address> addresses, addresses2;

    ServiceProvider serviceProvider;

    Customer customer;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "Maha", "12345", "Customer", customer,null);

        myUser2 = new MyUser(null, "Maha", "12345", "Provider", null,serviceProvider);

        address1 = new Address(null,"streeeeeeeet","riyadh","Alolya","12345","Saudi Arabia",customer,null);

        address2 = new Address(null,"streeeeeeeet","riyadh","Alolya","12345","Saudi Arabia",null,null);

        address3 = new Address(null,"streeeeeeeet","riyadh","Alolya","12345","Saudi Arabia",null,null);

        address4 = new Address(null,"streeeeeeeet","riyadh","Alolya","12345","Saudi Arabia",customer,null);


        addresses2 = new ArrayList<>();
        addresses2.add(address3);

        addresses = new ArrayList<>();
        addresses.add(address1);

        customer= new Customer(null,"Faisal","s@hotmail.com","0555555",myUser,addresses,null,null);
        serviceProvider= new ServiceProvider(null,"Faisal","s@hotmail.com","213312321","Electracity",1,"123241",myUser2, addresses2,null,null,null);


    }

    @Test
    public void getCustomerAddressesTest(){
            when(customerRepository.findCustomerByMyUser_Id(myUser.getId())).thenReturn(customer);
            List<Address> addressList = addressService.getCustomerAddresses(myUser.getId());
            Assertions.assertEquals(addressList,addresses);
            verify(customerRepository,times(1)).findCustomerByMyUser_Id(myUser.getId());
        }

    @Test
    public void getProviderAddressesTest(){
        when(serviceProviderRepository.findServiceProviderByMyUser_Id(myUser2.getId())).thenReturn(serviceProvider);
        List<Address> addressList = addressService.getProviderAddresses(myUser2.getId());
        Assertions.assertEquals(addressList,addresses2);
        verify(serviceProviderRepository,times(1)).findServiceProviderByMyUser_Id(myUser2.getId());
    }

    @Test
    public void addAddressToCustomerTest(){
        when(customerRepository.findCustomerByMyUser_Id(myUser.getId())).thenReturn(customer);
        addressService.addAddressToCustomer(address1,myUser.getId());
        verify(addressRepository,times(1)).save(address1);
    }

    @Test
    public void addAddressToProviderTest(){
        when(serviceProviderRepository.findServiceProviderByMyUser_Id(myUser2.getId())).thenReturn(serviceProvider);
        addressService.addAddressToProvider(address3,myUser2.getId());
        verify(addressRepository,times(1)).save(address3);
    }


}
