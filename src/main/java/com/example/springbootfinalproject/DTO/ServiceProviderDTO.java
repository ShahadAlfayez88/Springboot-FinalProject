package com.example.springbootfinalproject.DTO;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
public class ServiceProviderDTO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;

    @Column(unique = true)
    private String username;

    @NotNull
    @Column(nullable = false)
    private String password;

    @Pattern(regexp = "(Customer|Provider)", message = "Role must be in Customer or Service Provider only")
    @Column(columnDefinition = "varchar(10) not null check (role='Customer' or role='Provider')")
    private String role;

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

}
