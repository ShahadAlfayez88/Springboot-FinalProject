package com.example.springbootfinalproject.RepositoryTest;

import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import com.example.springbootfinalproject.Repository.ServicesRepository;
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
public class ServiceProviderRepositoryTest {
    @Autowired
    ServiceProviderRepository ServiceProviderRepository;

    ServiceProvider serviceProvider;

    MyUser myUser;

    @BeforeEach
    void setUp()
        {
            myUser=new MyUser(null,"Shahad" , "123" , "Customer",null,null );

        serviceProvider= new ServiceProvider(null,"Faisal","s@hotmail.com","213312321","Electracity",1,"123241",myUser,null,null,null,null);
    }

    @Test
    public void findServiceProviderById(){
        ServiceProviderRepository.save(serviceProvider);
        ServiceProvider serviceProvider1= ServiceProviderRepository.findServiceProviderById(serviceProvider.getId());
        Assertions.assertThat(serviceProvider1).isEqualTo(serviceProvider);
    }

    @Test
    public void findServiceProviderByMyUser_Id(){
        ServiceProviderRepository.save(serviceProvider);
        ServiceProvider serviceProvider1= ServiceProviderRepository.findServiceProviderByMyUser_Id(myUser.getId());
        Assertions.assertThat(serviceProvider1).isEqualTo(serviceProvider);
    }

    @Test
    public void findServiceProviderByName(){
        ServiceProviderRepository.save(serviceProvider);
        ServiceProvider serviceProvider1= ServiceProviderRepository.findServiceProviderByName(serviceProvider.getName());
        Assertions.assertThat(serviceProvider1).isEqualTo(serviceProvider);
    }
}
