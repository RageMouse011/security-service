package kz.dar.tech.securityservice.controller;

import kz.dar.tech.securityservice.dto.*;
import kz.dar.tech.securityservice.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/unauthorized")
@RequiredArgsConstructor
public class UnsecuredController {

    private final UserService userService;
    private final IndividualService individualService;
    private final IndividualEntrepreneurService individualEntrepreneurService;
    private final LimitedPartnershipService limitedPartnershipService;

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
}
