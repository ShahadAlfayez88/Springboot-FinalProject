package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.DTO.CustomerDTO;
import com.example.springbootfinalproject.DTO.ServiceProviderDTO;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/user")
public class MyUserController {

    private final MyUserService myUserService;


    // get users
    @GetMapping("/get-all")
    public ResponseEntity getAllBookingServices(){
        return ResponseEntity.status(HttpStatus.OK).body(myUserService.getAllUsers());
    }


    // All
//    @PostMapping("/login")
//    public ResponseEntity login(){
//        return ResponseEntity.status(200).body("Logged in successfully");
//    }

    // Add Customer
    @PostMapping("/customer/register")
    public ResponseEntity registerCustomer(@Valid @RequestBody CustomerDTO customerDTO){
        myUserService.addCustomer(customerDTO);
        return ResponseEntity.status(200).body("User(Customer) Created");
    }

    // Add Provider
    @PostMapping("/serviceProvider/register")
    public ResponseEntity registerServiceProvider(@Valid @RequestBody ServiceProviderDTO serviceProviderDTO){
        myUserService.addServiceProvider(serviceProviderDTO);
        return ResponseEntity.status(200).body("User(ServiceProvider) Created");
    }

    // update customer
    @PutMapping("/updateCustomer/{id}")
    public ResponseEntity updateCustomer(@Valid @RequestBody CustomerDTO customerDTO,@PathVariable Integer id){
        myUserService.updateCustomer(customerDTO, id);
        return ResponseEntity.status(200).body("User(Customer) updated");
    }

    // update ServiceProvider
    @PutMapping("/updateProvider/{id}")
    public ResponseEntity updateProvider(@Valid @RequestBody ServiceProviderDTO serviceProviderDTO,@PathVariable Integer id){
        myUserService.updateProvider(serviceProviderDTO, id);
        return ResponseEntity.status(200).body("User(ServiceProvider) updated");
    }


}
