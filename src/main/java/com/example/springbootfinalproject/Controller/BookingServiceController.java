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

    // get all orders in the system
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
    @PostMapping("/add/{service_id}/{customer_id}/{provider_id}")
    public ResponseEntity addBookingService(@RequestBody @Valid BookingService bookingService, @PathVariable Integer service_id,@PathVariable Integer customer_id,@PathVariable Integer provider_id){
        bookingServiceService.addBookingService(bookingService,service_id,customer_id,provider_id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking Service Done Successfully");
    }

    //change order Status
    @PutMapping("/changeStatus/{order_id}")
    public ResponseEntity updateBookingService(@RequestBody @Valid BookingService bookingService, @PathVariable Integer order_id){
        bookingServiceService.updateBookingService(bookingService,order_id);
        return ResponseEntity.status(HttpStatus.OK).body("Updated Done");
    }

    //delete BookingService
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteBookingService(@PathVariable Integer id){
        bookingServiceService.deleteBookingService(id);
        return ResponseEntity.status(HttpStatus.OK).body("Booking service deleted");
    }

}
