package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.BookingService;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Service.BookingServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-service")
public class BookingServiceController {
    private final BookingServiceService bookingServiceService;

    // get all orders in the system by ADMIN
    @GetMapping("/get-all")
    public ResponseEntity getAllBookingServices(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingServiceService.getAllBookingService());
    }

    //get BookingService by id
    //add user id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getBookingServiceById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(bookingServiceService.getBookingServiceById(id));
    }

    //add  BookingService
    //add user id -> customer id
    @PostMapping("/book/{service_id}/{provider_id}")
    public ResponseEntity addBookingService(@RequestBody @Valid BookingService bookingService, @PathVariable Integer service_id,@PathVariable Integer provider_id, @AuthenticationPrincipal MyUser auth){
        bookingServiceService.addBookingService(bookingService,service_id,auth.getId(),provider_id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking Service Done Successfully");
    }

    //change order Status
    @PutMapping("/changeStatus/{order_id}")
    public ResponseEntity updateBookingService(@RequestBody @Valid BookingService bookingService, @PathVariable Integer order_id, @AuthenticationPrincipal MyUser auth){
        bookingServiceService.updateBookingService(bookingService,order_id,auth.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Updated Done");
    }

//    //delete BookingService
//    //add user id
//    @DeleteMapping("/delete/{id}/{user_id}")
//    public ResponseEntity deleteBookingService(@PathVariable Integer id,@PathVariable Integer user_id){
//        bookingServiceService.deleteBookingService(id,user_id);
//        return ResponseEntity.status(HttpStatus.OK).body("Booking service deleted");
//    }

}
