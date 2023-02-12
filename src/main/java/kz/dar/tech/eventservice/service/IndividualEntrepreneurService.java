package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.dto.IndividualEntrepreneurRegisterRequest;
import kz.dar.tech.eventservice.entity.IndividualEntrepreneur;
import kz.dar.tech.eventservice.entity.Role;
import kz.dar.tech.eventservice.entity.UserList;
import kz.dar.tech.eventservice.repository.IndividualEntrepreneurRepository;
import kz.dar.tech.eventservice.repository.UserListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IndividualEntrepreneurService {

    private final IndividualEntrepreneurRepository individualEntrepreneurRepository;
    private final UserListRepository userListRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerIndividualEntrepreneur(IndividualEntrepreneurRegisterRequest request) {

        if (userListRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User has already defined in the system");
        }
        IndividualEntrepreneur individualEntrepreneur = IndividualEntrepreneur.builder()
                .iin(request.getIin())
                .firstname(request.getFirstName())
                .lastName(request.getLastName())
                .middleName(request.getMiddleName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();
        individualEntrepreneurRepository.save(individualEntrepreneur);
        UserList users = UserList.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();
        userListRepository.save(users);
        String jwtToken = jwtService.generateToken(individualEntrepreneur);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateIndividualEntrepreneur(AuthenticationRequest request) {
        String jwtToken = null;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        try {
            IndividualEntrepreneur individualEntrepreneur = individualEntrepreneurRepository.findByEmail(request.getEmail());
            jwtToken = jwtService.generateToken(individualEntrepreneur);
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
