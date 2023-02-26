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

    // get all Services by user id
    // add user id
    @GetMapping("/get-all/{user_id}")
    public ResponseEntity getAllServicesById(@PathVariable Integer user_id){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getAllServicesById(user_id));
    }

    //get Services by id
    // add user id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getServicesById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getServicesById(id));
    }

    //add  Services
    // add user id
    @PostMapping("/add/{provider_id}")
    public ResponseEntity addServices(@RequestBody @Valid Services services, @PathVariable Integer provider_id){
        serviceService.addServices(services,provider_id);
        return ResponseEntity.status(HttpStatus.OK).body("Services Added");
    }

    //update Services
    // add user id
    @PutMapping("/update/{userid}/{id}")
    public ResponseEntity updateServices(@RequestBody @Valid Services services, @PathVariable Integer userid,@PathVariable Integer id){
        serviceService.updateServices(services,userid,id);
        return ResponseEntity.status(HttpStatus.OK).body("Services Updated");
    }

    //delete Services
    // add user id
    @DeleteMapping("/delete/{userid}/{id}")
    public ResponseEntity deleteServices(@PathVariable Integer id, @PathVariable Integer userid){
        serviceService.deleteServices(id,userid);
        return ResponseEntity.status(HttpStatus.OK).body("Services deleted");
    }

    // get services by category
    // add user id
    @GetMapping("get-by-category/{user_id}/{category}")
    public ResponseEntity getServicesByCategory(@PathVariable Integer user_id, @PathVariable String category){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getByCategory(category, user_id));
    }


    // get services by name
// add user id
    @GetMapping("get-by-name/{user_id}/{name}")
    public ResponseEntity getServicesByName(@PathVariable Integer user_id, @PathVariable String name){
        return ResponseEntity.status(HttpStatus.OK).body(serviceService.getByName(name, user_id));
    }

}
