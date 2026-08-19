package com.jai.HireStack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jai.HireStack.model.Post;
import com.jai.HireStack.repository.PostRepository;
import com.jai.HireStack.repository.SearchRepositoryImpl;

@RestController
public class PostController
{
	@Autowired
	PostRepository repo;
	
	
	@Autowired
	SearchRepositoryImpl searchRepo;
	@GetMapping("/posts")
   public List<Post>  getAllPosts()
   {
	  return repo.findAll(); 
   }
	
	@PostMapping("/post")
	public Post addPost(@RequestBody Post post ) 
	{
		return repo.save(post);
	}
	
	@GetMapping("/posts/{text}")
	public List<Post> searchPost(@PathVariable String text){
		return searchRepo.findByText(text);
	}
	
}
