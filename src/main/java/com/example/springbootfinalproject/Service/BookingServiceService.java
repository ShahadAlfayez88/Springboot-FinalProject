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
    public void addBookingService(BookingService bookingService,Integer service_id, Integer user_id, Integer provider_id){

        // get service
        Services services=servicesRepository.findServicesById(service_id);

        // get provider
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(provider_id);

        // get customer
        Customer customer = customerRepository.findCustomerByMyUser_Id(user_id);
        if(services==null || serviceProvider==null ||customer==null){
            throw new ApiException("order or customer or provider Not Found!");
        }

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
    public void updateBookingService( BookingService bookingService,Integer id, Integer userid){
        BookingService NewbookingService=bookingServiceRepository.findBookingServicesById(id);
        MyUser myUser = myUserRepository.findMyUsersById(userid);

        if(NewbookingService==null || myUser==null){
            throw new ApiException("order or user Not Found!");
        }else if(NewbookingService.getServiceProvider().getMyUser().getId()!=userid){
            throw new ApiException("Sorry , You do not have the authority to update this order!");
        }

        NewbookingService.setId(id);
        NewbookingService.setStatus(bookingService.getStatus());
        NewbookingService.setAvailabilityDate(bookingService.getAvailabilityDate());
        NewbookingService.setAvailabilityTime(bookingService.getAvailabilityTime());


        bookingServiceRepository.save(NewbookingService);
    }

    //delete BookingService

    public void deleteBookingService(Integer id,Integer userid){
       BookingService bookingService=bookingServiceRepository.findBookingServicesById(id);
        if(bookingService==null){
            throw new ApiException("BookingService Not Found");
        }else if(bookingService.getServiceProvider().getMyUser().getId()!=userid){
            throw new ApiException("Sorry , You do not have the authority to update this order!");
        }
        bookingServiceRepository.delete(bookingService);
    }

    // get all customer's orders
    public List getAllCustomerOrders(Integer userid) {
        MyUser myUser = myUserRepository.findMyUsersById(userid);
        Customer customer = customerRepository.findCustomerByMyUser(myUser);
        if(myUser==null || customer==null){
            throw new ApiException("order or user Not Found!");
        }else if(customer.getMyUser().getId()!=userid){
            throw new ApiException("Sorry , You do not have the authority to view this order!");
        }
        return customer.getBookingServices();

    }


    // get all provider's orders
    public List getAllProviderOrders(Integer userid) {
        MyUser myUser = myUserRepository.findMyUsersById(userid);
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderByMyUser(myUser);
        if(myUser==null || serviceProvider==null){
            throw new ApiException("order or user Not Found!");
        }else if(serviceProvider.getMyUser().getId()!=userid){
            throw new ApiException("Sorry , You do not have the authority to view this order!");
        }
        return serviceProvider.getBookingServices();

    }
}
