package com.example.springbootfinalproject.Repository;

import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServiceProviderRepository extends JpaRepository<ServiceProvider,Integer> {

    ServiceProvider findServiceProviderById(Integer id);

    ServiceProvider findServiceProviderByName(String name);

    ServiceProvider findServiceProviderByMyUser(MyUser myUser);

}
