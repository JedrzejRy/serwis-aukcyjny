package com.example.serwisaukcyjny.model.services;

import com.example.serwisaukcyjny.model.Comment;
import com.example.serwisaukcyjny.model.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment save(Comment comment) {
        return commentRepository.save(comment);
    }



}
