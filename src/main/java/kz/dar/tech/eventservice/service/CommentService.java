package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.entity.Comment;
import kz.dar.tech.eventservice.entity.Event;
import kz.dar.tech.eventservice.entity.User;
import kz.dar.tech.eventservice.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.security.Principal;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final EventService eventService;
    private final UserService userService;


    public Comment postComment(Long eventId, Comment comment, Principal principal) {
        Event event = eventService.findEventById(eventId);
        User user = userService.findByUsername(principal.getName());
        comment.setEvent(event);
        comment.setUser(user);
        return commentRepository.save(comment);
    }
}
