package com.example.springbootfinalproject.ControllerTest;

import com.example.springbootfinalproject.Controller.CommentController;
import com.example.springbootfinalproject.Model.*;
import com.example.springbootfinalproject.Service.CommentService;
import com.example.springbootfinalproject.Service.ServicesService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
//اطفي السيكيورتي
@WebMvcTest(value = CommentController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CommentControllerTest {
    @MockBean
    CommentService commentService;

    @Autowired
    MockMvc mockMvc;

    MyUser myUser;

    ServiceProvider serviceProvider;

    Comment comment, comment2;

    List<Comment> commentList;

    @BeforeEach
    void setUp() {
        myUser = new MyUser(null, "Maha", "12345", "Customer", null,null);
        serviceProvider= new ServiceProvider(1,"Faisal","s@hotmail.com","213312321","Electracity",1,"123241",myUser,null,null,null,null);
        comment = new Comment(1,"------",2,null,serviceProvider);
        comment2 = new Comment(null,"------",2,null,serviceProvider);
        commentList = Arrays.asList(comment2);
    }

    // test add comment
    @Test
    public void addComment() throws  Exception{
        mockMvc.perform(post("/api/v1/comment/add/{provider_id}",serviceProvider.getId())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(comment)))
                .andExpect(status().isOk());
    }


    // test get comment by id
    @Test
    public void getCommentById() throws Exception{
        mockMvc.perform(get("/api/v1/comment/get-by-id/{id}",comment.getId()))
                .andExpect(status().isOk());

    }


    // test delete comment
    @Test
    public void testDeleteComment() throws Exception{
        mockMvc.perform(delete("/api/v1/comment/delete/{id}",comment.getId()))
                .andExpect(status().isOk());
    }


}
