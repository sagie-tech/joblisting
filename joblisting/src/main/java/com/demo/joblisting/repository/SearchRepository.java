package com.demo.joblisting.repository;

import com.demo.joblisting.model.Post;

import java.util.List;

public interface SearchRepository {

    List<Post> findByText(String text);


}
