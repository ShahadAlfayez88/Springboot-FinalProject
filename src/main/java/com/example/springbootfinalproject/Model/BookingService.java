package com.example.springbootfinalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @NotNull(message = "availability Date Should Not Be Empty ")
    private Integer NumberOfServices;
    @NotNull(message = "availability Date Should Not Be Empty ")
    private Integer totalPrice;

    Date dateReceived=new Date();
    @NotNull(message = "availability Date Should Not Be Empty ")
    @Pattern(regexp = "^(new||inProgress||completed)$",message = "status should be :new or inProgress or completed only ")
    private  String status;
    @NotNull(message = "availability Date Should Not Be Empty ")
    @Pattern(regexp = "^(3pm-8pm||8am-2pm)$",message = "availabilityTime should be :3pm-8pm or 8am-2pm only ")
    private String availabilityTime;
    @NotNull(message = "availability Date Should Not Be Empty ")
    private String availabilityDate;
}
