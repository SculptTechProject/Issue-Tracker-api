package pl.dalke.issue_tracker_api.auth;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.dalke.issue_tracker_api.user.UserEntity;
import pl.dalke.issue_tracker_api.user.UserRepository;
import pl.dalke.issue_tracker_api.user.UserRole;

import java.util.UUID;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthService(
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService
    ) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    public AuthResponse register(RegisterRequest request) {
        if (request.login() == null || request.login().isBlank()) {
            throw new IllegalArgumentException("Login cannot be null or blank");
        }

        if (request.password() == null || request.password().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }

        if (userRepository.existsByLogin(request.login())) {
            throw new IllegalArgumentException("User with this login already exists");
        }

        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setLogin(request.login());
        user.setPasswordHash(passwordEncoder.encode(request.password()));
        user.setRole(UserRole.USER);

        UserEntity savedUser = userRepository.save(user);

        String token = jwtService.generateToken(savedUser);

        return new AuthResponse(
                savedUser.getId(),
                savedUser.getLogin(),
                savedUser.getRole(),
                token
        );
    }

    public AuthResponse login(LoginRequest request) {
        if (request.login() == null || request.login().isBlank()) {
            throw new IllegalArgumentException("Login cannot be null or blank");
        }

        if (request.password() == null || request.password().isBlank()) {
            throw new IllegalArgumentException("Password cannot be null or blank");
        }

        UserEntity user = userRepository.findByLogin(request.login())
                .orElseThrow(() -> new IllegalArgumentException("Invalid login or password"));

        boolean passwordMatches = passwordEncoder.matches(
                request.password(),
                user.getPasswordHash()
        );

        if (!passwordMatches) {
            throw new IllegalArgumentException("Invalid login or password");
        }

        String token = jwtService.generateToken(user);

        return new AuthResponse(
                user.getId(),
                user.getLogin(),
                user.getRole(),
                token
        );
    }
}