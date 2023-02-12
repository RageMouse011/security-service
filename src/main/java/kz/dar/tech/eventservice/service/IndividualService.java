package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.dto.*;
import kz.dar.tech.eventservice.entity.*;
import kz.dar.tech.eventservice.repository.IndividualRepository;
import kz.dar.tech.eventservice.repository.UserListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualService {

    private final IndividualRepository individualRepository;
    private final UserListRepository userListRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerIndividual(IndividualRegisterRequest request) {
        if (userListRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User has already defined in system");
        }
        Individual individual = Individual.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();
        individualRepository.save(individual);
        UserList users = UserList.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();
        userListRepository.save(users);
        String jwtToken = jwtService.generateToken(individual);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateIndividual(AuthenticationRequest request) {
        String jwtToken = null;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        try {
            Individual individual = individualRepository.findByEmail(request.getEmail());
            jwtToken = jwtService.generateToken(individual);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (jwtToken != null) {
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .build();
        } else {
            throw new IllegalStateException("JWT is null.");
        }
    }
}
