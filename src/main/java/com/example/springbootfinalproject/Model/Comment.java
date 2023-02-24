package com.example.springbootfinalproject.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotNull(message = "Message Should not be Empty")
    private String message;
    @NotNull(message = "Rating Should not be Empty")
    @Min(1)
    @Max(5)
    private Integer rating;

    // Second Relationship - one to many [ customer has many comments]
    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    @JsonIgnore
    Customer customer;

    @ManyToOne
    @JoinColumn(name = "provider_id", referencedColumnName = "id")
    @JsonIgnore
    ServiceProvider serviceProvider;


}
