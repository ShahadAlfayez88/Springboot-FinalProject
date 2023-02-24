package com.example.springbootfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotNull(message = "Street Should not be Empty")
    @Size(min = 10,message = "Street Should not be less than 10")
    private String street;
    @NotNull(message = "City Should not be Empty")
    private String city;
    @NotNull(message = "neighborhood Should not be Empty")
    private  String neighborhood;
    @NotNull(message = "postCode Should not be Empty")
    @Size(max = 5,message = "Postcode Should not be less than 10")
    private String postCode;
    @NotNull(message = "Country Should not be Empty")
    private  String country;

    // First Relationship - one to many [ customer has many addresses]

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    Customer customer;


    // Second Relationship - one to many [ Provider has many addresses]

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    @JsonIgnore
    ServiceProvider serviceProvider;
}
