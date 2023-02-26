package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Objects;

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
    // add user id
    @PutMapping("/update/{id}")
    public ResponseEntity updateCustomer(@Valid @RequestBody Customer Customer, @PathVariable Integer id) {

        customerService.updateCustomer(id, Customer);
        return ResponseEntity.status(200).body("Customer is updated ");
    }

    //delete
    // add user id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer id){
        customerService.deleteCustomer(id);
        return ResponseEntity.status(200).body("Customer is deleted ");
    }



    // get company by name
    // add user id
    @GetMapping("/getCompany/{name}")
    public ResponseEntity getCompanyByName(@PathVariable String name){
        CompanyDetails serviceProvider = customerService.getCompany(name);
        return ResponseEntity.status(200).body(serviceProvider);
    }

    // get company's comments by name
    // add user id
    @GetMapping("/getComment/{name}")
    public ResponseEntity getCommentsByName(@PathVariable String name){
        List<Comment> comment = customerService.getComment(name);
        return ResponseEntity.status(200).body(comment);
    }

    // get all orders in the system by user id
    @GetMapping("/get-all-orders/{user_id}")
    public ResponseEntity getAllBookingServicesByUserId(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllBookingServiceById(user_id));
    }

    // get order detail
    // add user id
    @GetMapping("/getOrder/{customer_id}/{order_id}")
    public ResponseEntity getOrder(@PathVariable Integer customer_id,@PathVariable Integer order_id){
        Object bookingService = customerService.getOrderByID(customer_id,order_id);
        return ResponseEntity.status(200).body(bookingService);
    }

}
