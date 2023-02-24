package com.example.springbootfinalproject.Service;

import com.example.springbootfinalproject.Exception.ApiException;
import com.example.springbootfinalproject.Model.Comment;
import com.example.springbootfinalproject.Model.Customer;
import com.example.springbootfinalproject.Model.ServiceProvider;
import com.example.springbootfinalproject.Repository.CommentRepository;
import com.example.springbootfinalproject.Repository.CustomerRepository;
import com.example.springbootfinalproject.Repository.ServiceProviderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    private final CustomerRepository customerRepository;

    private final ServiceProviderRepository serviceProviderRepository;

    //get all Comments
    public List<Comment> getAllComments(){
        return commentRepository.findAll();
    }
    //get Comment by id
        public Comment getCommentById(Integer id){
        Comment comment=commentRepository.findCommentById(id);
        if (comment==null){
            throw new ApiException("Comment Not Found!");
        }
        return comment;
    }

    //add Comment
    public void addComment(Comment comment, Integer customer_id, Integer provider_id){
        ServiceProvider serviceProvider = serviceProviderRepository.findServiceProviderById(provider_id);
        Customer customer = customerRepository.findCustomerById(customer_id);

        // assign comment
        comment.setCustomer(customer);
        comment.setServiceProvider(serviceProvider);
        commentRepository.save(comment);

        // assign customer
        customer.getComments().add(comment);
        serviceProvider.getComments().add(comment);
        customerRepository.save(customer);
        serviceProviderRepository.save(serviceProvider);

    }

    //update Comment
    public void updateComment( Comment comment,Integer id){
        Comment oldComment=commentRepository.findCommentById(id);
        if(oldComment==null){
            throw new ApiException("Comment Not Found");
        }
        oldComment.setId(id);
        oldComment.setMessage(comment.getMessage());
        oldComment.setRating(comment.getRating());

        commentRepository.save(oldComment);
    }
    //delete Comment

    public void deleteComment(Integer id){
        Comment comment=commentRepository.findCommentById(id);
        if(comment==null){
            throw new ApiException("Comment Not Found");
        }
        commentRepository.delete(comment);
    }
}
