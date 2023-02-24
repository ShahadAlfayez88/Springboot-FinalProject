package com.example.springbootfinalproject.Repository;

import com.example.springbootfinalproject.Model.Services;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServicesRepository extends JpaRepository<Services,Integer> {
    Services findServicesById(Integer id);
}
