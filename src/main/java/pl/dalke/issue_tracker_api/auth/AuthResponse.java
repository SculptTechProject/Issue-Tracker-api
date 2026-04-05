package pl.dalke.issue_tracker_api.auth;

import pl.dalke.issue_tracker_api.user.UserRole;

import java.util.UUID;

public record AuthResponse(
        UUID id,
        String login,
        UserRole role,
        String token
) {
}
