package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.entity.Comment;
import kz.dar.tech.eventservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }
}
