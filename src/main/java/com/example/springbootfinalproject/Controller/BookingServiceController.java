package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.BookingService;
import com.example.springbootfinalproject.Service.BookingServiceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/book-service")
public class BookingServiceController {
    private final BookingServiceService bookingServiceService;
    // get all BookingService
    @GetMapping("/get-all")
    public ResponseEntity getAllBookingServices(){
        return ResponseEntity.status(HttpStatus.OK).body(bookingServiceService.getAllBookingService());
    }

    //get BookingService by id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getBookingServiceById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(bookingServiceService.getBookingServiceById(id));
    }

    //add  BookingService
    @PostMapping("/add/{service_id}")
    public ResponseEntity addBookingService(@RequestBody @Valid BookingService bookingService, @PathVariable Integer service_id){
        bookingServiceService.addBookingService(bookingService,service_id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Booking Service Done Successfully"));
    }
    //update Address
    @PutMapping("/update/{id}")
    public ResponseEntity updateBookingService(@RequestBody @Valid BookingService bookingService, @PathVariable Integer id){
        bookingServiceService.updateBookingService(bookingService,id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException(" Updated Done"));
    }

    //delete BookingService
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookingService(@PathVariable Integer id){
        bookingServiceService.deleteBookingService(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Booking service deleted"));
    }

}
