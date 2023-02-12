package kz.dar.tech.eventservice.controller;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.entity.Comment;
import kz.dar.tech.eventservice.entity.Event;
import kz.dar.tech.eventservice.entity.User;
import kz.dar.tech.eventservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api/authorized")
@RequiredArgsConstructor
public class SecuredController {

    private final UserService userService;
    private final IndividualService individualService;
    private final IndividualEntrepreneurService individualEntrepreneurService;
    private final LimitedPartnershipService limitedPartnershipService;
    private final EventService eventService;
    private final CommentService commentService;

    @PostMapping("/authenticate/user")
    public AuthenticationResponse authenticateUser(
            @RequestBody AuthenticationRequest request
    ) {
        return userService.authenticateUser(request);
    }

    @PostMapping("/authenticate/individual")
    public AuthenticationResponse authenticateIndividual(
            @RequestBody AuthenticationRequest request
    ) {
        return individualService.authenticateIndividual(request);
    }

    @PostMapping("/authenticate/individualEntrepreneur")
    public AuthenticationResponse authenticateIndividualEntrepreneur(
            @RequestBody AuthenticationRequest request
    ) {
        return individualEntrepreneurService.authenticateIndividualEntrepreneur(request);
    }

    @PostMapping("/authenticate/limitedPartnership")
    public AuthenticationResponse authenticateLimitedPartnership(
            @RequestBody AuthenticationRequest request
    ) {
        return limitedPartnershipService.authenticateLimitedPartnership(request);
    }

    @PostMapping("/post/event")
    public Event postEvent(
            @RequestBody Event event) {
        return eventService.postEvent(event);
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
