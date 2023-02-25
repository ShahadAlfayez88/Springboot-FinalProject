package com.example.springbootfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

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

    @OneToOne(cascade=CascadeType.ALL)
    @JsonIgnore
    private MyUser myUser;

    // First Relationship - one to many [ customer has many addresses]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    List<Address> address;

    // Second Relationship - one to many [ customer has many comments]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    List<Comment> comments;

    // Third Relationship - one to many [ customer has many orders]
    @OneToMany(cascade = CascadeType.ALL,mappedBy = "customer")
    List<BookingService> bookingServices;








}
