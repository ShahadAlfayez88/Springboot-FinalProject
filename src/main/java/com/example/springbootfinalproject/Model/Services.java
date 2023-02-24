package com.example.springbootfinalproject.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import lombok.*;

@Entity
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Services {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Integer id;
    @NotNull(message = "Name Should not be Empty")
    private String name;
    @NotNull(message = "Description Should not be Empty")
    private String description;
    @NotNull(message = "Category Should not be Empty")
    @Pattern(regexp = "^(Plumbing||Electricity||Moving Furniture||Conditioning||Paints||General Cleaning||Carpentry||Blacksmithing)$",message = "Category should be :\n" +
            "1-Plumbing\n" +
            "2-Electricity\n" +
            "3-Moving Furniture" +
            "4-Conditioning\n" +
            "5-Paints\n" +
            "6-General Cleaning\n"+
            "7-Carpentry\n"+
            "8-Blacksmithing")
    private String category;
    //لازم نضيف العلاقات
    @NotNull(message = "Price Should not be Empty")
    @Positive
    private Integer price;
    @NotNull(message = "followingPeriod Should not be Empty")
    @Min(value = 3,message = "Following Period Should not less than 3")
    private Integer followingPeriod;
}
