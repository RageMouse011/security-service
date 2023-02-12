package kz.dar.tech.eventservice.service;

import kz.dar.tech.eventservice.dto.AuthenticationRequest;
import kz.dar.tech.eventservice.dto.AuthenticationResponse;
import kz.dar.tech.eventservice.dto.UserRegisterRequest;
import kz.dar.tech.eventservice.entity.Role;
import kz.dar.tech.eventservice.entity.User;
import kz.dar.tech.eventservice.entity.UserList;
import kz.dar.tech.eventservice.repository.UserListRepository;
import kz.dar.tech.eventservice.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserListRepository userListRepository;

    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse registerUser(UserRegisterRequest request) {
        if (userListRepository.existsByEmail(request.getEmail())) {
            throw new IllegalStateException("User has already defined in system");
        }
        User user = User.builder()
                .firstname(request.getFirstName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userRepository.save(user);
        UserList users = UserList.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        userListRepository.save(users);
        String jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticateUser(AuthenticationRequest request) {
        String jwtToken = null;
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        try {
            User user = userRepository.findByEmail(request.getEmail());
            jwtToken = jwtService.generateToken(user);
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

    public User findByUsername(String username) {
        return userRepository.findByEmail(username);
    }
}
