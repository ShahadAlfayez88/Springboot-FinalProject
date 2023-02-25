package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Service.AddressService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/address")
public class AddressController {
    private final AddressService addressService;

    // get all Addresses
    @GetMapping("/get-all")
    public ResponseEntity getAllAddresses(){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAllAddresses());
    }

    //get Address by id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getAddressById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(addressService.getAddressById(id));
    }

    //add  Address and assign it to customer
    @PostMapping("/customer/add/{customer_id}")
    public ResponseEntity addAddressToCustomer(@RequestBody @Valid Address address, @PathVariable Integer customer_id){
        addressService.addAddressToCustomer(address,customer_id);
        return ResponseEntity.status(HttpStatus.OK).body("Address Added");
    }

    //add  Address and assign it to service provider
    @PostMapping("/provider/add/{provider_id}")
    public ResponseEntity addAddressToProvider(@RequestBody @Valid Address address, @PathVariable Integer provider_id){
        addressService.addAddressToProvider(address,provider_id);
        return ResponseEntity.status(HttpStatus.OK).body("Address Added");
    }


    //update Address
    @PutMapping("/customer/update/{userid}/{id}")
    public ResponseEntity updateCustomerAddress(@RequestBody @Valid Address address, @PathVariable Integer userid,@PathVariable Integer id){
        addressService.updateCustomerAddress(address,userid,id);
        return ResponseEntity.status(HttpStatus.OK).body("Address Updated");
    }

    //update Address
    @PutMapping("/provider/update/{userid}/{id}")
    public ResponseEntity updateProviderAddress(@RequestBody @Valid Address address, @PathVariable Integer userid,@PathVariable Integer id){
        addressService.updateProviderAddress(address,userid,id);
        return ResponseEntity.status(HttpStatus.OK).body("Address Updated");
    }
    //delete Address
    @DeleteMapping("/delete/{userid}/{id}")
    public ResponseEntity deleteAddress(@PathVariable Integer id,@PathVariable Integer userid){
        addressService.deleteAddress(id,userid);
        return ResponseEntity.status(HttpStatus.OK).body("Address deleted");
    }

}
