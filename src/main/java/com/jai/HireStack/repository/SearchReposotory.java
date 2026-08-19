package com.jai.HireStack.repository;

import java.util.List;

import com.jai.HireStack.model.Post;

public interface SearchReposotory 
{
   List<Post> findByText(String text);
}
