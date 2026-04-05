package pl.dalke.issue_tracker_api.auth;

public record RegisterRequest (
        String login,
        String password
) { }
