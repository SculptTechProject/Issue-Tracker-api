package pl.dalke.issue_tracker_api.user;

import java.util.UUID;

public record UserResponse (
        UUID id,
        String login,
        UserRole role
) { }
