package kz.dar.tech.securityservice.service;

import kz.dar.tech.securityservice.dto.AuthenticationRequest;
import kz.dar.tech.securityservice.dto.AuthenticationResponse;
import kz.dar.tech.securityservice.dto.IndividualEntrepreneurRegisterRequest;
import kz.dar.tech.securityservice.entity.IndividualEntrepreneur;
import kz.dar.tech.securityservice.entity.Role;
import kz.dar.tech.securityservice.entity.UserList;
import kz.dar.tech.securityservice.repository.IndividualEntrepreneurRepository;
import kz.dar.tech.securityservice.repository.UserListRepository;
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
