package com.demo.joblisting.repository;

import com.demo.joblisting.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.springframework.stereotype.Component;

import org.bson.Document;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
@Component
public class SearchRepositoryImpl implements SearchRepository{
    @Autowired
    MongoClient client;
    @Autowired
    MongoConverter converter;
    @Override
    public List<Post> findByText(String text) {

       final List<Post> posts = new ArrayList<>();

        MongoDatabase db= client.getDatabase("job");
        MongoCollection<org.bson.Document> collection = db.getCollection("JobPost");
        AggregateIterable<Document> result = collection.aggregate(Arrays.asList(new Document("$search",
                        new Document("text", new Document("query", text)
                                .append("path", Arrays.asList("techs", "desc", "profile")))),
                        new Document("$sort",
                        new Document("exp", 1L)),
                        new Document("$limit", 5L)));
        result.forEach(doc->posts.add(converter.read(Post.class, doc)));

        return posts;

    }
}
