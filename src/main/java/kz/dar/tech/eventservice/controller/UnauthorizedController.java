package kz.dar.tech.eventservice.controller;

import kz.dar.tech.eventservice.dto.*;
import kz.dar.tech.eventservice.entity.Event;
import kz.dar.tech.eventservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unauthorized")
@RequiredArgsConstructor
public class UnauthorizedController {

    private final UserService userService;
    private final IndividualService individualService;
    private final IndividualEntrepreneurService individualEntrepreneurService;
    private final LimitedPartnershipService limitedPartnershipService;
    private final EventService eventService;

    @PostMapping("/register/user")
    public AuthenticationResponse registerUser(
            @RequestBody UserRegisterRequest request
    ) {
        return userService.registerUser(request);
    }

    @PostMapping("/register/individual")
    public AuthenticationResponse registerIndividual(
            @RequestBody IndividualRegisterRequest request
    ) {
        return individualService.registerIndividual(request);
    }

    @PostMapping("/register/individualEntrepreneur")
    public AuthenticationResponse registerIndividualEntrepreneur(
            @RequestBody IndividualEntrepreneurRegisterRequest request
    ) {
        return individualEntrepreneurService.registerIndividualEntrepreneur(request);
    }

    @PostMapping("/register/limitedPartnership")
    public AuthenticationResponse registerLimitedPartnership(
            @RequestBody LimitedPartnershipRegisterRequest request
    ) {
        return limitedPartnershipService.registerLimitedPartnership(request);
    }

    @PostMapping("/authenticate/user")
    public AuthenticationResponse authenticateUser(
            @RequestBody AuthenticationRequest request
    ) {
        return userService.authenticateUser(request);
    }

    @PostMapping("/authenticate/limitedPartnership")
    public AuthenticationResponse authenticateLimitedPartnership(
            @RequestBody AuthenticationRequest request
    ) {
        return limitedPartnershipService.authenticateLimitedPartnership(request);
    }

    @GetMapping("events/all")
    public List<Event> getAllEvents() {
        return eventService.getAllEvents();
    }

    @GetMapping("/events/{section}")
    public List<Event> getEventsBySection(
            @PathVariable String section) {
        return eventService.getEventsBySection(section);
    }
}
