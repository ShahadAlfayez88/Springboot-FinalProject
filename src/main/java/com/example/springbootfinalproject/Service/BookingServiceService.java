package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookingServiceService {
    private final BookingServiceRepository bookingServiceRepository;
    private  final ServicesRepository servicesRepository;

    private final ServiceProviderRepository serviceProviderRepository;

    private final CustomerRepository customerRepository;

    private final MyUserRepository myUserRepository;

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
    public void addBookingService(BookingService bookingService,Integer service_id, Integer customer_id, Integer provider_id){

        // get service
        Services services=servicesRepository.findServicesById(service_id);

        // get provider
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(provider_id);

        // get customer
        Customer customer = customerRepository.findCustomerById(customer_id);


        bookingService.setServiceProvider(serviceProvider);
        bookingService.setCustomer(customer);
        bookingService.setServices(services);
        bookingService.setStatus("new");

        Double vat = services.getPrice()*0.15;

        bookingService.setTotalPrice(services.getPrice()+vat);
        bookingServiceRepository.save(bookingService);

        // assign order
        customer.getBookingServices().add(bookingService);
        serviceProvider.getBookingServices().add(bookingService);
        serviceProviderRepository.save(serviceProvider);
        customerRepository.save(customer);
    }

    //update BookingService
    public void updateBookingService( BookingService bookingService,Integer id){
        BookingService NewbookingService=bookingServiceRepository.findBookingServicesById(id);
        if(NewbookingService==null){
            throw new ApiException("BookingService Not Found");
        }
        NewbookingService.setId(id);
        NewbookingService.setStatus(bookingService.getStatus());
        NewbookingService.setAvailabilityDate(bookingService.getAvailabilityDate());
        NewbookingService.setAvailabilityTime(bookingService.getAvailabilityTime());


        bookingServiceRepository.save(NewbookingService);
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
