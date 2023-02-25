package com.example.springbootfinalproject.Model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CompanyDetails {
    private String name;


    private String email;

    private String phoneNumber;

    private String SpecialisedAt;

    private Integer yearsOfExperience;

    private String CommercialRegister;

    private List<Address> address;

}
