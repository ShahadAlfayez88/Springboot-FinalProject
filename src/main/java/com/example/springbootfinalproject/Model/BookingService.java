package com.example.springbootfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.Date;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookingService {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double totalPrice;

    Date dateReceived=new Date();
    @NotNull(message = "status Should Not Be Empty ")
    @Pattern(regexp = "^(new||inProgress||completed)$",message = "status should be :new or inProgress or completed only ")
    private  String status;
    @NotNull(message = "availability Time Should Not Be Empty ")
    @Pattern(regexp = "^(3pm-8pm||8am-2pm)$",message = "availabilityTime should be :3pm-8pm or 8am-2pm only ")
    private String availabilityTime;
    @NotNull(message = "availability Date Should Not Be Empty ")
    private String availabilityDate;

    // First Relationship - many to one [ orders has one customer]
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    Customer customer;

    // First Relationship - many to one [ orders has one provider]
    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    @JsonIgnore
    ServiceProvider serviceProvider;

    // Second Relationship - many to one [ orders has one service]
    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    @JsonIgnore
    Services services;
}
