package com.codegym.cms.controller;


import com.codegym.cms.model.Post;
import com.codegym.cms.service.post.IPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    IPostService postService;

    @GetMapping("/posts")
    public ResponseEntity<Iterable<Post>> findAllPost() {
        List<Post> posts = (List<Post>) postService.findAll();
        if (posts.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(posts, HttpStatus.OK);
    }

    @GetMapping("/posts/{id}")
    public ResponseEntity<Post> findPostById(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(postOptional.get(), HttpStatus.OK);
    }

    @PostMapping("/posts")
    public ResponseEntity<Post> savePost(@RequestBody Post post) {
        return new ResponseEntity<>(postService.save(post), HttpStatus.CREATED);
    }

    @PutMapping("/posts/{id}")
    public ResponseEntity<Post> updatePost(@PathVariable Long id, @RequestBody Post post) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        post.setId(postOptional.get().getId());
        return new ResponseEntity<>(postService.save(post), HttpStatus.OK);
    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<Post> deletePost(@PathVariable Long id) {
        Optional<Post> postOptional = postService.findById(id);
        if (!postOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        postService.remove(id);
        return new ResponseEntity<>(postOptional.get(), HttpStatus.NO_CONTENT);
    }
}