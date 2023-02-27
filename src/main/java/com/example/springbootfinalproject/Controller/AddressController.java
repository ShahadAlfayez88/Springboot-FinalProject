package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    // get all Addresses

    @GetMapping("/get-all")
    public ResponseEntity getAllAddresses(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAdress());
    }


    @GetMapping("/customer/get-all")
    public ResponseEntity getCustomerAddresses(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getCustomerAddresses(auth.getId()));
    }

    // get all Addresses
    @GetMapping("/provider/get-all")
    public ResponseEntity getProviderAddresses(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getProviderAddresses(auth.getId()));
    }


    //add user id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getAddressById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressById(id));
    }

    //add  address and assign it to customer
    @PostMapping("/customer/add")
    public ResponseEntity addAddressToCustomer(@RequestBody @Valid Address address,@AuthenticationPrincipal MyUser auth){
        addressService.addAddressToCustomer(address,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Address Added");
    }

    //add Address and assign it to service provider
    @PostMapping("/provider/add")
    public ResponseEntity addAddressToProvider(@RequestBody @Valid Address address,@AuthenticationPrincipal MyUser auth){
        addressService.addAddressToProvider(address,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Address Added");
    }


    //update customer Address
    @PutMapping("/customer/update/{id}")
    public ResponseEntity updateCustomerAddress(@RequestBody @Valid Address address,@AuthenticationPrincipal MyUser auth,@PathVariable Integer id){
        addressService.updateCustomerAddress(address, auth.getId(), id);
        return ResponseEntity.status(HttpStatus.OK).body("Address Updated");
    }

    //update provider Address
    @PutMapping("/provider/update/{id}")
    public ResponseEntity updateProviderAddress(@RequestBody @Valid Address address, @AuthenticationPrincipal MyUser auth,@PathVariable Integer id){
        addressService.updateProviderAddress(address,auth.getId(),id);
        return ResponseEntity.status(HttpStatus.OK).body("Address Updated");
    }

    //delete customer Address
    @DeleteMapping("/customer/delete/{address_id}")
    public ResponseEntity deleteCustomerAddress(@PathVariable Integer address_id,@AuthenticationPrincipal MyUser auth){
        addressService.deleteCustomerAddress(address_id, auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Address deleted");
    }

    //delete provider Address
    @DeleteMapping("/provider/delete/{address_id}")
    public ResponseEntity deleteProviderAddress(@PathVariable Integer address_id,@AuthenticationPrincipal MyUser auth){
        addressService.deleteProviderAddress(address_id,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Address deleted");
    }

}
