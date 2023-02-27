package com.example.springbootfinalproject.ServiceTest;
import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Repository.*;
import com.example.springbootfinalproject.Service.AddressService;
import com.example.springbootfinalproject.Service.ServicesService;
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
public class ServicesServicesTest {
    // اوصل للservice

    @InjectMocks
    ServicesService servicesService;
    @Mock
    ServicesRepository servicesRepository;


    @Mock
    ServiceProviderRepository serviceProviderRepository;

    MyUser myUser;


    List<Services> servicesList;

    ServiceProvider serviceProvider;

    Services service1,service2 ;




    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "Maha", "12345", "Customer", null,null);


        service1 = new Services(null,"maintence","------","Plumbing",20,7,serviceProvider,null);
        service2 = new Services(null,"clean","------","General Cleaning",20,7,serviceProvider,null);

        servicesList = new ArrayList<>();
        servicesList.add(service1);
        serviceProvider= new ServiceProvider(null,"Faisal","s@hotmail.com","213312321","Electracity",1,"123241",myUser, null,null,servicesList,null);


    }

    @Test
    public void getAllServicesTest(){
        when(servicesRepository.findAll()).thenReturn(servicesList);
        List<Services> servicesList1 = servicesService.getAllServices();
        Assertions.assertEquals(1,servicesList1.size());
        verify(servicesRepository,times(1)).findAll();
    }

    @Test
    public void getAllProviderServicesTest(){
        when(serviceProviderRepository.findServiceProviderByMyUser_Id(myUser.getId())).thenReturn(serviceProvider);
        List<Services> servicesList1 = servicesService.getAllServicesById(myUser.getId());
        Assertions.assertEquals(servicesList1,servicesList);
        verify(serviceProviderRepository,times(1)).findServiceProviderByMyUser_Id(myUser.getId());
    }



}

