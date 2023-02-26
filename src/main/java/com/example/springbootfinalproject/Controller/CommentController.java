package com.example.springbootfinalproject.Controller;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Address;
import com.example.springbootfinalproject.Model.Comment;
import com.example.springbootfinalproject.Service.CommentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/comment")
public class CommentController {
    private final CommentService commentService;

    // get all Comments
    @GetMapping("/get-all")
    public ResponseEntity getAllComments(){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllComments());
    }

    //get Comment by id
    //add user id
    @GetMapping("get-by-id/{id}")
    public ResponseEntity getCommentById(@PathVariable Integer id ){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getCommentById(id));
    }

    //add Comment
    //add user id
    @PostMapping("/add/{customer_id}/{provider_id}")
    public ResponseEntity addAddress(@RequestBody @Valid Comment comment,@PathVariable Integer customer_id,@PathVariable Integer provider_id){
        commentService.addComment(comment,customer_id,provider_id);
        return ResponseEntity.status(HttpStatus.OK).body("Comment Added");
    }
    //update Comment
    // add user id
    @PutMapping("/update/{id}")
    public ResponseEntity updateComment(@RequestBody @Valid Comment comment, @PathVariable Integer id){
        commentService.updateComment(comment,id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Comment Updated"));
    }

    //delete Comment
    // add user id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteComment(@PathVariable Integer id){
        commentService.deleteComment(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ApiException("Comment deleted"));
    }

}
