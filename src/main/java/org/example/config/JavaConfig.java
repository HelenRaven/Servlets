package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.example.controller.PostController;
import org.example.repository.PostRepository;
import org.example.service.PostService;

@Configuration
public class JavaConfig {
    @Bean
    // название метода - название бина
    public PostController postController() {
        // вызов метода и есть DI
        return new PostController(postService());
    }

    @Bean
    public PostService postService() {
        return new PostService(postRepository());
    }

    @Bean
    public PostRepository postRepository() {
        return new PostRepository();
    }
}
