package com.example.springbootfinalproject.Model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OrderDetail {

    private String name;

    private String phoneNumber;

    private List<Address> address;

}
