package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.BookingService;
import com.example.springbootfinalproject.Model.Services;
import com.example.springbootfinalproject.Repository.BookingServiceRepository;
import com.example.springbootfinalproject.Repository.ServicesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceService {
    private final BookingServiceRepository bookingServiceRepository;
    private  final ServicesRepository servicesRepository;

    //get all BookingServices
    public List<BookingService> getAllBookingService(){
        return bookingServiceRepository.findAll();
    }
    //get BookingService by id
    public BookingService getBookingServiceById(Integer id){
        BookingService bookingService=bookingServiceRepository.findBookingServicesById(id);
        if (bookingService==null){
            throw new ApiException("Booking Service Not Found!");
        }
        return bookingService;
    }
    //add BookingService
    public void addBookingService(BookingService bookingService,Integer service_id){
        Services services=servicesRepository.findServicesById(service_id);
        if (service_id==null){
            throw new ApiException("Service Not Found ");
        }
        bookingService.setStatus("new");
        bookingService.setTotalPrice(services.getPrice());
        bookingServiceRepository.save(bookingService);
    }

    //update BookingService
    public void updateBookingService( BookingService bookingService,Integer id){
        BookingService oldBookingService=bookingServiceRepository.findBookingServicesById(id);
        if(oldBookingService==null){
            throw new ApiException("BookingService Not Found");
        }
        oldBookingService.setId(id);
        oldBookingService.setAvailabilityDate(bookingService.getAvailabilityDate());
        oldBookingService.setAvailabilityTime(bookingService.getAvailabilityTime());


        bookingServiceRepository.save(oldBookingService);
    }
    //delete BookingService

    public void deleteBookingService(Integer id){
       BookingService bookingService=bookingServiceRepository.findBookingServicesById(id);
        if(bookingService==null){
            throw new ApiException("BookingService Not Found");
        }
        bookingServiceRepository.delete(bookingService);
    }
}
