package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.ApiResponse;
import com.example.springbootfinalproject.DTO.ServiceProviderDTO;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Service.MyUserService;
import com.example.springbootfinalproject.Service.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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
        return ResponseEntity.status(200).body(new ApiResponse("Provider is updated "));
    }

    //delete
    // add user id
    @DeleteMapping("/delete/{userId}")
    public ResponseEntity deleteProvider(@PathVariable Integer userId){
        providerService.deleteProvider(userId);
        return ResponseEntity.status(200).body(new ApiResponse("Provider is deleted "));
    }

    // get order detail
    // add user id
    @GetMapping("/getOrder/{order_id}")
    public ResponseEntity getOrder(@AuthenticationPrincipal MyUser auth, @PathVariable Integer order_id){
        Object bookingService = providerService.getOrderByID(auth.getId(),order_id);
        return ResponseEntity.status(200).body(bookingService);
    }

    // get all orders in the system by user id
    // add user id
    @GetMapping("/get-all")
    public ResponseEntity getAllBookingServicesByUserId(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(HttpStatus.OK).body(providerService.getAllBookingServiceById(auth.getId()));
    }


}
