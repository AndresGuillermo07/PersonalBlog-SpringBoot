package com.roadmap.personal_blog.controllers;

import com.roadmap.personal_blog.entities.Post;
import com.roadmap.personal_blog.services.IPostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Optional;

@Controller
public class PostPageController {

    private final IPostService postService;

    public PostPageController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/new")
    public String postPage(Model model) {

        model.addAttribute("date", LocalDate.now().toString());
        model.addAttribute("post", new Post());

        return "post";
    }

    @PostMapping("/new")
    public String postPageSubmit(@ModelAttribute Post post) {

        Post newPost = new Post(post.getTitle(),post.getContent(),LocalDate.now());

        postService.addPost(newPost);

        return "redirect:/home";
    }

    @PostMapping("/edit/{idPost}")
    public String update(@PathVariable Long idPost, @ModelAttribute Post post) {
        Optional<Post> existingPost = Optional.ofNullable(postService.findPostById(idPost));
        if (existingPost.isPresent()) {
            Post updatedPost = existingPost.get();
            updatedPost.setTitle(post.getTitle());
            updatedPost.setContent(post.getContent());
            updatedPost.setDate(post.getDate());
            postService.addPost(updatedPost);
        }
        return "redirect:/home";
    }

    @GetMapping("/delete/{idPost}")
    public String delete(@PathVariable Long idPost) {

        postService.deletePost(idPost);

        return "redirect:/home";
    }

}
