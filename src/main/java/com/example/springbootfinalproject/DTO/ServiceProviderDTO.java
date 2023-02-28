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

}
