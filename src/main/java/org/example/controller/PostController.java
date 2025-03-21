package org.example.controller;

import com.google.gson.Gson;
import org.example.model.Post;
import org.example.service.PostService;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Reader;

public class PostController {
    public static final String APPLICATION_JSON = "application/json";
    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    public void all(HttpServletResponse response) throws IOException {
        final var data = service.all();
        sendResponse(data, response);
    }

    public void getById(long id, HttpServletResponse response) throws IOException {
        final var data = service.getById(id);
        sendResponse(data, response);
    }

    public void save(Reader body, HttpServletResponse response) throws IOException {
        final var gson = new Gson();
        final var post = gson.fromJson(body, Post.class);
        final var data = service.save(post);
        sendResponse(data, response);
    }

    public void removeById(long id, HttpServletResponse response) throws IOException {
        service.removeById(id);
        sendResponse("ok", response);
    }

    private void sendResponse(Object data, HttpServletResponse response) throws IOException {
        response.setContentType(APPLICATION_JSON);
        final var gson = new Gson();
        response.getWriter().print(gson.toJson(data));
    }
}
