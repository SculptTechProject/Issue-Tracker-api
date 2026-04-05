package pl.dalke.issue_tracker_api.user;

public record CreateUserRequest(
        String login,
        String password,
        UserRole role
) {}