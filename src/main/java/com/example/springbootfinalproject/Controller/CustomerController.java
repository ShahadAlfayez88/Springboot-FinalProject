package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.ApiResponse;
import com.example.springbootfinalproject.DTO.CustomerDTO;
import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Service.CustomerService;
import com.example.springbootfinalproject.Service.MyUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
    @GetMapping("/get-all")
    public ResponseEntity getAllCustomers(){
        List<Customer> Customers = customerService.getCustomer();
        return ResponseEntity.status(200).body(Customers);
    }


    //update
    // add user id
    @PutMapping("/update/{user_id}")
    public ResponseEntity updateCustomer(@Valid @RequestBody Customer Customer, @PathVariable Integer user_id) {

        customerService.updateCustomer(user_id, Customer);
        return ResponseEntity.status(200).body(new ApiResponse("Customer is updated "));
    }

    //delete
    // add user id
    @DeleteMapping("/delete/{user_id}")
    public ResponseEntity deleteCustomer(@PathVariable Integer user_id){
        customerService.deleteCustomer(user_id);
        return ResponseEntity.status(200).body(new ApiResponse("Customer is deleted"));
    }



    // get company by name

    @GetMapping("/getCompany/{name}")
    public ResponseEntity getCompanyByName(@PathVariable String name){
        CompanyDetails serviceProvider = customerService.getCompany(name);
        return ResponseEntity.status(200).body(serviceProvider);
    }

    // get company's comments by name

    @GetMapping("/getComment/{name}")
    public ResponseEntity getCommentsByName(@PathVariable String name){
        List<Comment> comment = customerService.getComment(name);
        return ResponseEntity.status(200).body(comment);
    }

    // get all orders in the system by user id
    @GetMapping("/get-all-orders")
    public ResponseEntity getAllBookingServicesByUserId( @AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(HttpStatus.OK).body(customerService.getAllBookingServiceById(auth.getId()));
    }

    // get order detail
    // add user id
    @GetMapping("/getOrder/{order_id}")
    public ResponseEntity getOrder( @AuthenticationPrincipal MyUser auth,@PathVariable Integer order_id){
        Object bookingService = customerService.getOrderByID(auth.getId(),order_id);
        return ResponseEntity.status(200).body(bookingService);
    }

}
