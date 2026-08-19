package com.jai.HireStack.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.jai.HireStack.model.Post;

public interface PostRepository extends MongoRepository<Post, String> {  // ✅ naya
}