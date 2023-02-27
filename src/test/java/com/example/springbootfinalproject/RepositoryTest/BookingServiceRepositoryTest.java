package com.example.springbootfinalproject.RepositoryTest;

import com.example.springbootfinalproject.Model.BookingService;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Repository.BookingServiceRepository;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BookingServiceRepositoryTest {
    @Autowired
    BookingServiceRepository bookingServiceRepository;
    BookingService bookingService;

    @BeforeEach
    void setUp()
    {
        bookingService=new BookingService(null,12.5,null,"completed","3pm-8pm","2023-01-1",null,null,null);

    }

    @Test
    public void findCustomerById(){
        bookingServiceRepository.save(bookingService);
        BookingService bookingService1= bookingServiceRepository.findBookingServicesById(bookingService.getId());
        Assertions.assertThat(bookingService1).isEqualTo(bookingService);
    }
}
