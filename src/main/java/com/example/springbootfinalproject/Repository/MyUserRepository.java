package com.example.springbootfinalproject.Repository;

import com.example.springbootfinalproject.Model.MyUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyUserRepository extends JpaRepository<MyUser,Integer> {
    MyUser findMyUsersById(Integer id);
    MyUser findMyUsersByUsername(String username);
}
