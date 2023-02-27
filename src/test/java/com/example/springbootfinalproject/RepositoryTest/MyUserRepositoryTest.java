package com.example.springbootfinalproject.RepositoryTest;

import com.example.springbootfinalproject.Model.MyUser;
import com.example.springbootfinalproject.Repository.MyUserRepository;
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
public class MyUserRepositoryTest {

    @Autowired
    MyUserRepository myUserRepository;

    MyUser myUser;

    @BeforeEach
    void setUp() {
        myUser=new MyUser(null,"Shahad" , "123" , "Customer",null,null );
    }

    @Test
    public void findMyUserById(){
        myUserRepository.save(myUser);
        MyUser myUser1 = myUserRepository.findMyUsersById(myUser.getId());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }
    @Test
    public void findMyUsersByUsername(){
        myUserRepository.save(myUser);
        MyUser myUser1 = myUserRepository.findMyUsersByUsername(myUser.getUsername());
        Assertions.assertThat(myUser1).isEqualTo(myUser);
    }

}
