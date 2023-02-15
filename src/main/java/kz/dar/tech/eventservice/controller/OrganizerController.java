package kz.dar.tech.eventservice.controller;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.entity.Event;
import kz.dar.tech.eventservice.service.EventService;
import kz.dar.tech.eventservice.service.IndividualEntrepreneurService;
import kz.dar.tech.eventservice.service.IndividualService;
import kz.dar.tech.eventservice.service.LimitedPartnershipService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
@RequiredArgsConstructor
@RequestMapping("/api/authorized/organizer")
public class OrganizerController {

    private final EventService eventService;
    private final IndividualService individualService;
    private final IndividualEntrepreneurService individualEntrepreneurService;
    private final LimitedPartnershipService limitedPartnershipService;

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

    @PostMapping("/post/event")
    public Event postEvent(
            @RequestBody Event event) {
        return eventService.postEvent(event);
    }
}
