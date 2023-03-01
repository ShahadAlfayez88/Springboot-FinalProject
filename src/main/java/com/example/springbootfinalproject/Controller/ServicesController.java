package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.ApiResponse;
import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Service.ServicesService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    // get all Services by user id
    // add user id
    @GetMapping("/provider/get-all")
    public ResponseEntity getAllServicesById(@AuthenticationPrincipal MyUser auth){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getAllServicesById(auth.getId()));
    }

    //get Services by id
    // add user id
    @GetMapping("/get-by-id/{id}")
    public ResponseEntity getServicesById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getServicesById(id));
    }

    //add  Services
    @PostMapping("/add")
    public ResponseEntity addServices(@RequestBody @Valid Services services,@AuthenticationPrincipal MyUser auth){
        serviceService.addServices(services,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Services Added"));
    }

    //update Services
    @PutMapping("/update/{id}")
    public ResponseEntity updateServices(@RequestBody @Valid Services services,@AuthenticationPrincipal MyUser auth,@PathVariable Integer id){
        serviceService.updateServices(services,auth.getId(),id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Services Updated"));
    }

    //delete Services
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteServices(@PathVariable Integer id, @AuthenticationPrincipal MyUser auth){
        serviceService.deleteServices(id,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Services deleted"));
    }

    // get services by category
    @GetMapping("/get-by-category/{category}")
    public ResponseEntity getServicesByCategory( @PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getByCategory(category));
    }


    // get services by name
    @GetMapping("/get-by-name/{name}")
    public ResponseEntity getServicesByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getByName(name));
    }

}
