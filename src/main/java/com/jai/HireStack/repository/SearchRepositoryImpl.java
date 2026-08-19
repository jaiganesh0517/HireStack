package com.jai.HireStack.repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.convert.MongoConverter;
import org.bson.Document;
import org.springframework.stereotype.Component;

import com.jai.HireStack.model.Post;
import com.mongodb.client.AggregateIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;


@Component
public class SearchRepositoryImpl implements SearchReposotory
{
	@Autowired
	MongoClient client;
	
	@Autowired
	MongoConverter mc;

	@Override
	public List<Post> findByText(String text) {

	    MongoDatabase database = client.getDatabase("HireStack");
	    MongoCollection<Document> collection = database.getCollection("JobPost");

	    AggregateIterable<Document> result = collection.aggregate(Arrays.asList(
	        new Document("$search",
	            new Document("text",
	                new Document("query", text)   
	                    .append("path", new Document("wildcard", "*"))  
	            )
	        ),
	        new Document("$sort", new Document("exp", 1L)),
	        new Document("$limit", 5L)
	    ));

	    List<Post> posts = new ArrayList<>();
	    result.forEach(doc -> posts.add(mc.read(Post.class, doc)));
	    return posts;
	}
	}


