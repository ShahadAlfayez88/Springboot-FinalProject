package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {
    final private CustomerService customerService;


    //display
    @GetMapping("/display")
    public ResponseEntity getAllCustomers(){
        List<Customer> Customers = customerService.getCustomer();
        return ResponseEntity.status(200).body(Customers);
    }


    //update
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@Valid @RequestBody Customer Customer, @PathVariable Integer id) {

        customerService.updateCustomer(id, Customer);
        return ResponseEntity.status(200).body("Customer is updated ");
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer is deleted ");
    }



}
