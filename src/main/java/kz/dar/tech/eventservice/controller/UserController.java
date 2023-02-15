package kz.dar.tech.eventservice.controller;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.entity.Comment;
import kz.dar.tech.eventservice.service.CommentService;
import kz.dar.tech.eventservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/authorized/user")
public class UserController {

    private final CommentService commentService;
    private final UserService userService;

//    @PostMapping("/authenticate")
//    public AuthenticationResponse authenticateUser(
//            @RequestBody AuthenticationRequest request
//    ) {
//        return userService.authenticateUser(request);
//    }

    @PostMapping("/events/{eventId}/comments")
    public Comment postComment(
            @PathVariable Long eventId,
            @RequestBody Comment comment,
            Principal principal) {
        return commentService.postComment(eventId, comment, principal);
    }
}
