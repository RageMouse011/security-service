package kz.dar.tech.securityservice.controller;

import kz.dar.tech.securityservice.dto.AuthenticationRequest;
import kz.dar.tech.securityservice.dto.AuthenticationResponse;
import kz.dar.tech.securityservice.service.IndividualEntrepreneurService;
import kz.dar.tech.securityservice.service.IndividualService;
import kz.dar.tech.securityservice.service.LimitedPartnershipService;
import kz.dar.tech.securityservice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/authorized")
@RequiredArgsConstructor
public class SecuredController {

    private final UserService userService;
    private final IndividualService individualService;
    private final IndividualEntrepreneurService individualEntrepreneurService;
    private final LimitedPartnershipService limitedPartnershipService;

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
}
