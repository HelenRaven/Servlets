package org.example.repository;

import org.example.exception.NotFoundException;
import org.example.model.Post;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class PostRepository {
    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();
    private final AtomicLong counter = new AtomicLong(1);

    public List<Post> all() {
        return new ArrayList<>(posts.values());
    }

    public Optional<Post> getById(long id) {
        try {
            return Optional.ofNullable(posts.get((int) id));
        } catch (IndexOutOfBoundsException ignored) {
            return Optional.empty();
        }
    }

    public Post save(Post post) {
        if (post.getId() == 0 || !posts.containsKey((int) post.getId())) {
            create(post);
        } else {
            posts.put((int) post.getId(), post);
        }
        return post;
    }

    public void removeById(long id) {
        try {
            posts.remove((int) id);
        } catch (UnsupportedOperationException | ClassCastException | NullPointerException ignored) {
            throw new NotFoundException();
        }
    }

    private void create (Post post){
        long index = counter.getAndIncrement();
        post.setId(index);
        posts.put((int) index, post);
    }
}
