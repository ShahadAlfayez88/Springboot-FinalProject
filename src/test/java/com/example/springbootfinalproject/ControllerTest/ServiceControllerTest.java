package com.example.springbootfinalproject.ControllerTest;

import com.example.springbootfinalproject.Controller.ServicesController;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Service.ServicesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

@ExtendWith(SpringExtension.class)
//اطفي السيكيورتي
@WebMvcTest(value = ServicesController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ServiceControllerTest {

    @MockBean
    ServicesService servicesService;

    @Autowired
    MockMvc mockMvc;

    MyUser myUser;

    ServiceProvider serviceProvider;

    Services service1, service2, service3;

    List<Services> services, servicesList;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "Maha", "12345", "Customer", null,null);
        serviceProvider= new ServiceProvider(null,"Faisal","s@hotmail.com","213312321","Electracity",1,"123241",myUser,null,null,null,null);
        service1= new Services(null,"service1","-------","Plumbing",20,7,serviceProvider,null);
        service2= new Services(null,"service2","-------","Plumbing",20,7,serviceProvider,null);
        service3= new Services(1,"service3","-------","Plumbing",20,7,null,null);

        services = Arrays.asList(service1);
        servicesList = Arrays.asList(service2);

    }

    // test add service
    @Test
    public void addService() throws  Exception{
        mockMvc.perform(post("/api/v1/service/add")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(service1)))
                .andExpect(status().isOk());
    }

    // test get all service
    @Test
    public void getAllServices() throws Exception{
        Mockito.when(servicesService.getAllServices()).thenReturn(services);
        mockMvc.perform(get("/api/v1/service/get-all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("service1"));
    }

    // test get all provider service
    @Test
    public void getAllServicesByProviderId() throws Exception{
        Mockito.when(servicesService.getAllServicesById(myUser.getId())).thenReturn(servicesList);
        mockMvc.perform(get("/api/v1/service/provider/get-all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("service2"));
    }

    // test get service by id
    @Test
    public void getServiceById() throws Exception{
            mockMvc.perform(get("/api/v1/service/get-by-id/{id}",service3.getId()))
                    .andExpect(status().isOk());

    }

    // test delete
    @Test
    public void testDeleteService() throws Exception{
        mockMvc.perform(delete("/api/v1/service/delete/{id}",service3.getId()))
                .andExpect(status().isOk());
    }

    // test get by category
    @Test
    public void testGetByCategory() throws Exception{
        mockMvc.perform(get("/api/v1/service/get-by-category/{category}",service1.getCategory()))
                .andExpect(status().isOk());
    }

    // test get by name
    @Test
    public void testGetByName() throws Exception{
        mockMvc.perform(get("/api/v1/service/get-by-name/{name}",service1.getName()))
                .andExpect(status().isOk());
    }



}
