package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.dto.LimitedPartnershipRegisterRequest;
import kz.dar.tech.eventservice.entity.LimitedPartnership;
import kz.dar.tech.eventservice.entity.Role;
import kz.dar.tech.eventservice.entity.UserList;
import kz.dar.tech.eventservice.repository.LimitedPartnershipRepository;
import kz.dar.tech.eventservice.repository.UserListRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LimitedPartnershipService {

    private final LimitedPartnershipRepository limitedPartnershipRepository;
    private final UserListRepository userListRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerLimitedPartnership(LimitedPartnershipRegisterRequest request) {
        if (userListRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User has already defined in system");
        }
        LimitedPartnership limitedPartnership = LimitedPartnership.builder()
                .bin(request.getBin())
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();
        limitedPartnershipRepository.save(limitedPartnership);
        UserList users = UserList.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ORGANIZER)
                .build();
        userListRepository.save(users);
        String jwtToken = jwtService.generateToken(limitedPartnership);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateLimitedPartnership(AuthenticationRequest request) {
        String jwtToken = null;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        try {
            LimitedPartnership limitedPartnership = limitedPartnershipRepository.findByEmail(request.getEmail());
            jwtToken = jwtService.generateToken(limitedPartnership);
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
