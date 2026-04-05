package pl.dalke.issue_tracker_api.user;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UserResponse createUser(CreateUserRequest request) {
        UserEntity user = new UserEntity();
        user.setId(UUID.randomUUID());
        user.setLogin(request.login());

        String encodedPassword = passwordEncoder.encode(request.password());
        user.setPasswordHash(encodedPassword);

        user.setRole(request.role());

        if(user.getRole() == null) {
            user.setRole(UserRole.USER);
        }

        UserEntity savedUser = userRepository.save(user);

        return new UserResponse(
                savedUser.getId(),
                savedUser.getLogin(),
                savedUser.getRole()
        );
    }

    public UserEntity getUserById(UUID id) {
        return userRepository.findById(id).orElseThrow();
    }
}
