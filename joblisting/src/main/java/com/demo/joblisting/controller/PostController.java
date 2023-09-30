package com.demo.joblisting.controller;

import com.demo.joblisting.PostRepository;
import com.demo.joblisting.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {
    @Autowired
    PostRepository repo;
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

//    @GetMapping("search/{text}/")
//    public List<Post> searchPost(@PathVariable String text)
//    {
//
//    }
}
