package com.example.springbootfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Pattern(regexp = "^(Plumbing||Electricity||Moving Furniture||Conditioning||Paints||General Cleaning||Carpentry||Blacksmithing)$",message = "Category should be :\n" +
            "1-Plumbing\n" +
            "2-Electricity\n" +
            "3-Moving Furniture" +
            "4-Conditioning\n" +
            "5-Paints\n" +
            "6-General Cleaning\n"+
            "7-Carpentry\n"+
            "8-Blacksmithing")
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

    // First Relationship - one to many [ provider has many addresses]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceProvider")
    List<Address> address;

    // Second Relationship - one to many [ provider has many comments]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceProvider")
    List<Comment> comments;

    // Third Relationship - one to many [ provider has many services]

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceProvider")
    List<Services> services;

    @OneToMany(cascade = CascadeType.ALL,mappedBy = "serviceProvider")
    List<BookingService> bookingServices;



}
