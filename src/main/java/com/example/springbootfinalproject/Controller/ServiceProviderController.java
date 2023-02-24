package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Service.ProviderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    @PutMapping("/update/{id}")
    public ResponseEntity updateProvider(@Valid @RequestBody ServiceProvider Provider, @PathVariable Integer id) {

        providerService.updateProvider(id, Provider);
        return ResponseEntity.status(200).body("Provider is updated ");
    }

    //delete
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProvider(@PathVariable Integer id){
        providerService.deleteProvider(id);
        return ResponseEntity.status(200).body("Provider is deleted ");
    }


}
