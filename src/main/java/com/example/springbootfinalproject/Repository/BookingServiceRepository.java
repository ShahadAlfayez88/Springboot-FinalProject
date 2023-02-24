package com.example.springbootfinalproject.Repository;

import com.example.springbootfinalproject.Model.BookingService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookingServiceRepository extends JpaRepository<BookingService,Integer> {
    BookingService findBookingServicesById(Integer id);
}
