package kz.dar.tech.eventservice.controller;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.entity.Comment;
import kz.dar.tech.eventservice.entity.Event;
import kz.dar.tech.eventservice.entity.User;
import kz.dar.tech.eventservice.service.CommentService;
import kz.dar.tech.eventservice.service.EventService;
import kz.dar.tech.eventservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequiredArgsConstructor
@RequestMapping("api/authorized/user")
public class SecuredUserController {

    private final EventService eventService;
    private final CommentService commentService;
    private final UserService userService;


    @PostMapping("/authenticate/user")
    public AuthenticationResponse authenticateUser(
            @RequestBody AuthenticationRequest request
    ) {
        return userService.authenticateUser(request);
    }

    @PostMapping("/events/{eventId}/comments")
    public Comment postComment(
            @PathVariable Long eventId,
            @RequestBody Comment comment,
            Principal principal) {
        Event event = eventService.findEventById(eventId);
        User user = userService.findByUsername(principal.getName());
        comment.setEvent(event);
        comment.setUser(user);
        Comment savedCommend = commentService.saveComment(comment);
        return savedCommend;
    }
}
