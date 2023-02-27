package com.example.springbootfinalproject.RepositoryTest;

import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Repository.MyUserRepository;
import com.example.springbootfinalproject.Repository.ServicesRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ServiceRepsitoryTest {
    @Autowired
    ServicesRepository servicesRepository;

    Services services, service1, service2;

    @BeforeEach
    void setUp() {
        services= new Services(null,"Maintenance","-------","Plumbing",20,7,null,null);
        service1= new Services(null,"Maintenance","-------","Plumbing",20,7,null,null);
        service2= new Services(null,"Maintenance","-------","Plumbing",20,7,null,null);

    }

    @Test
    public void findServicesById(){
        servicesRepository.save(services);
        Services services1 = servicesRepository.findServicesById(services.getId());
        Assertions.assertThat(services1).isEqualTo(services);
    }

    @Test
    public void findServicesByName(){
        servicesRepository.save(services);
        Services services1 = servicesRepository.findServicesByName(services.getName());
        Assertions.assertThat(services1).isEqualTo(services);
    }

    // عندي شك
    @Test
    public void findAllByMyUserTesting(){
        servicesRepository.save(service1);
        servicesRepository.save(service2);
        List<Services> servicesList= servicesRepository.findAllByCategory("Plumbing");
        Assertions.assertThat(servicesList.get(0).getCategory()).isEqualTo(service1.getCategory());
    }
}
