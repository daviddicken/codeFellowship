package com.daviddicken.codeFellowship.controllers;

import com.daviddicken.codeFellowship.models.users.Post;
import com.daviddicken.codeFellowship.models.users.PostRepository;
import com.daviddicken.codeFellowship.models.users.Users;
import com.daviddicken.codeFellowship.models.users.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import java.sql.Date;

@Controller
public class PostController {
    @Autowired
    UsersRepository usersRepository;

    @Autowired
    PostRepository postRepository;

    @PostMapping("/savepost")
    public RedirectView addPost(long id, String body){

        Post newPost = new Post(body);
        Users author = usersRepository.getOne(id);

        newPost.user = author;

        postRepository.save(newPost);

        author.posts.add(newPost);
        usersRepository.save(author);

        return new RedirectView("/userInfo/" + author.getUserName());

    }
}
