package com.roadmap.personal_blog.services;

import com.roadmap.personal_blog.entities.Post;
import com.roadmap.personal_blog.repositories.IPostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements IPostService {

    private final IPostRepository postRepository;

    public PostServiceImpl(IPostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public void addPost(Post post) {
        postRepository.save(post);
    }

    @Override
    public void deletePost(Long idPost) {
        postRepository.deleteById(idPost);
    }

    @Override
    public void update(Long idPost, Post newPost) {
        Optional<Post> existingPost = postRepository.findById(idPost);
        existingPost.ifPresent(post -> {
            post.setTitle(newPost.getTitle());
            post.setContent(newPost.getContent());
            post.setDate(newPost.getDate());
            postRepository.save(post);
        });
    }

    @Override
    public List<Post> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public Post findPostById(Long idPost) {
        return postRepository.findById(idPost).orElse(null);
    }
}
