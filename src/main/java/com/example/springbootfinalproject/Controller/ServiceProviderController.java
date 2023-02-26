package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Service.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/provider")
public class ServiceProviderController {
    
    private final ProviderService providerService;


    //display
    @GetMapping("/display")
    public ResponseEntity getAllProviders(){
        List<ServiceProvider> Providers = providerService.getProvider();
        return ResponseEntity.status(200).body(Providers);
    }


    //update
    // add user id
    @PutMapping("/update/{id}")
    public ResponseEntity updateProvider(@Valid @RequestBody ServiceProvider Provider, @PathVariable Integer id) {

        providerService.updateProvider(id, Provider);
        return ResponseEntity.status(200).body("Provider is updated ");
    }

    //delete
    // add user id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProvider(@PathVariable Integer id){
        providerService.deleteProvider(id);
        return ResponseEntity.status(200).body("Provider is deleted ");
    }

    // get order detail
    // add user id
    @GetMapping("/getOrder/{order_id}")
    public ResponseEntity getOrder(@PathVariable Integer provider_id,@PathVariable Integer order_id){
        Object bookingService = providerService.getOrderByID(provider_id,order_id);
        return ResponseEntity.status(200).body(bookingService);
    }

    // get all orders in the system by user id
    // add user id
    @GetMapping("/get-all/{user_id}")
    public ResponseEntity getAllBookingServicesByUserId(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(providerService.getAllBookingServiceById(user_id));
    }


}
