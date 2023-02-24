package com.example.springbootfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ServiceProvider {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @NotNull(message = "Name Should not be Empty")
    private String name;

    @NotNull(message = "email Should not be Empty")
    @Email
    private String email;

    @NotNull(message = "phone Number Should not be Empty")
    private String phoneNumber;

    @NotNull(message = "Specialised At Should not be Empty")
    private String SpecialisedAt;

    @NotNull(message = "years Of Experience Should not be Empty")
    private Integer yearsOfExperience;

    @NotNull(message = "Commercial Register Should not be Empty")
    private String CommercialRegister;


    // relationship - 1
    @OneToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private MyUser myUser;

    // First Relationship - one to many [ customer has many addresses]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceProvider")
    List<Address> address;

    // Second Relationship - one to many [ customer has many comments]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceProvider")
    List<Comment> comments;


}
