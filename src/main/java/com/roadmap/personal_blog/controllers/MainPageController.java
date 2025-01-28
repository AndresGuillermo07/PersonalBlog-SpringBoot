package com.roadmap.personal_blog.controllers;

import com.roadmap.personal_blog.entities.Post;
import com.roadmap.personal_blog.services.IPostService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class MainPageController {

    private final IPostService postService;

    public MainPageController(IPostService postService) {
        this.postService = postService;
    }

    @GetMapping("/")
    public String redirectToMainPage() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("DELETE"));
        if(isAdmin){
            return "redirect:/admin";
        }
        else
            return "redirect:/home";
    }

    @GetMapping("/home")
    public String mainPage(Model model) {

        List<Post> posts = postService.getPosts();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("DELETE"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("posts", posts);

        return "index";
    }


    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String mainAdminPage(Model model) {

        List<Post> posts = postService.getPosts();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(role -> role.getAuthority().equals("DELETE"));

        model.addAttribute("isAdmin", isAdmin);
        model.addAttribute("posts", posts);

        return "index";
    }


    @GetMapping("/article/{idPost}")
    public String article(@PathVariable Long idPost, Model model) {

        Post post = postService.findPostById(idPost);

        model.addAttribute("title",post.getTitle());
        model.addAttribute("content",post.getContent());
        model.addAttribute("date",post.getDate());

        return "detail";
    }

    @GetMapping("/edit/{idPost}")
    public String edit(@PathVariable Long idPost, Model model) {

        Post post = postService.findPostById(idPost);

        model.addAttribute("post",post);
        model.addAttribute("date", LocalDate.now());

        return "edit";
    }

}
