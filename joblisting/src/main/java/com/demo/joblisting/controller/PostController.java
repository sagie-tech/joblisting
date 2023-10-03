package com.demo.joblisting.controller;

import com.demo.joblisting.repository.PostRepository;
import com.demo.joblisting.model.Post;
import com.demo.joblisting.repository.SearchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepository repo;
    @Autowired
    SearchRepository srepo;
    @GetMapping("/posts")
    public List<Post> getAllPosts()
    {
        return repo.findAll();
    }

    @PostMapping("/addPost")
    public Post addPost(@RequestBody Post post)
    {
        return repo.save(post);
    }

    @GetMapping("search/{text}")
   public List<Post> searchPost(@PathVariable String text)
    {
         return   srepo.findByText(text);
    }
}
