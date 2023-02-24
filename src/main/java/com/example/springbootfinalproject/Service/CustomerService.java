package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.DTO.CustomerDTO;
import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;

    // By Admin
    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }

    // Get Customer info by id

    //CRUD

    // Get - display
    public List<Customer> getCustomer() {
        return customerRepository.findAll();
    }

    // Update

    public void updateCustomer(Integer id, Customer customer) {
        Customer oldCustomer = customerRepository.findCustomerById(id);
        if (oldCustomer == null) {
            throw new ApiException("Id not found!!");
        }
        customer.setId(id);
        customerRepository.save(customer);
    }



    // Delete
    public void deleteCustomer(Integer id) {
        Customer Customer = customerRepository.findCustomerById(id);
        if (!customerRepository.existsById(id)) {
            throw new ApiException("Id is not found");
        }
        customerRepository.delete(Customer);
//        myUserRepository.delete();
    }








}
