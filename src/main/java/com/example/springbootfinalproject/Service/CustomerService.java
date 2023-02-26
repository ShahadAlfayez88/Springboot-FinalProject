package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.DTO.CustomerDTO;
import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final ServiceProviderRepository serviceProviderRepository;
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

    // get company by name
    public CompanyDetails getCompany(String name){
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByName(name);
        if(serviceProvider==null){
            throw new ApiException("Company not found");
        }
        CompanyDetails companyDetails = new CompanyDetails(serviceProvider.getName(),serviceProvider.getPhoneNumber(),serviceProvider.getEmail(),serviceProvider.getSpecialisedAt(),serviceProvider.getYearsOfExperience(),serviceProvider.getCommercialRegister(),serviceProvider.getAddress());
        return companyDetails;
    }


    // get order by id

    public Object getOrderByID(Integer customer_id, Integer order_id) {

        Customer customer = customerRepository.findCustomerById(customer_id);

        List<Object> details = new ArrayList<>();

        for(int i = 0 ; i<customer.getBookingServices().size();i++){

            if(customer.getBookingServices().get(i).getId()==order_id){
                OrderDetail orderDetail = new OrderDetail( customer.getBookingServices().get(i).getServiceProvider().getName(),customer.getBookingServices().get(i).getServiceProvider().getPhoneNumber(),customer.getBookingServices().get(i).getServiceProvider().getAddress());
                details.add(customer.getBookingServices().get(i));
                details.add(orderDetail);
                return details;
            }
        }

        throw new ApiException("Order not found");
    }

    // get comment by company name

    public List<Comment> getComment(String name) {

        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByName(name);
        if(serviceProvider==null){
            throw new ApiException("Company not found");
        }
        return serviceProvider.getComments();

    }


    //get all orders by id
    public Object getAllBookingServiceById(Integer userId) {
        MyUser myUser = myUserRepository.findMyUsersById(userId);
        Customer customer= customerRepository.findCustomerByMyUser(myUser);

        if (myUser==null || customer ==null){
            throw new ApiException("Not Found!");
        }
        return customer.getBookingServices();
    }
}
