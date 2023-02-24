package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Service.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/service")
public class ServicesController {
    private final ServicesService serviceService;
// get all Services
    @GetMapping("/get-all")
    public ResponseEntity getAllServices(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getAllServices());
    }

    //get Services by id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getServicesById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getServicesById(id));
    }

    //add  Services
    @PostMapping("/add")
    public ResponseEntity addServices(@RequestBody @Valid Services services){
        serviceService.addServices(services);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Services Added"));
    }
    //update Services
    @PutMapping("/update/{id}")
    public ResponseEntity updateServices(@RequestBody @Valid Services services, @PathVariable Integer id){
        serviceService.updateServices(services,id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Services Updated"));
    }

    //delete Services
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteServices(@PathVariable Integer id){
        serviceService.deleteServices(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Services deleted"));
    }

}
