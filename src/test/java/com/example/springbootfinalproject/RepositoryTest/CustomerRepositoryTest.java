package com.example.springbootfinalproject.RepositoryTest;

import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CustomerRepositoryTest {
    @Autowired
    CustomerRepository customerRepository;
    Customer customer;

    MyUser myUser;

    @BeforeEach
    void setUp()
    {
        myUser=new MyUser(null,"Shahad" , "123" , "Customer",null,null );

        customer= new Customer(null,"Faisal","s@hotmail.com","0555555",myUser,null,null,null);
    }

    @Test
    public void findCustomerById(){
        customerRepository.save(customer);
        Customer customer1= customerRepository.findCustomerById(customer.getId());
        Assertions.assertThat(customer1).isEqualTo(customer);
    }

    @Test
    public void findCustomerByMyUser_Id(){
        customerRepository.save(customer);
        Customer customer1= customerRepository.findCustomerByMyUser_Id(myUser.getId());
        Assertions.assertThat(customer1).isEqualTo(customer);
    }

    @Test
    public void findCustomerByMyUser(){
        customerRepository.save(customer);
        Customer customer1= customerRepository.findCustomerByMyUser(myUser);
        Assertions.assertThat(customer1).isEqualTo(customer);
    }
}



