package com.roadmap.personal_blog.services;

import com.roadmap.personal_blog.entities.Post;

import java.util.List;

public interface IPostService {

    void addPost(Post post);

    void deletePost(Long idPost);

    List<Post> getPosts();

    Post findPostById(Long idPost);

    public void update(Long idPost, Post newPost);

}
