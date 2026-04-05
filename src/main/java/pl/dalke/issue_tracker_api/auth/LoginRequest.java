package pl.dalke.issue_tracker_api.auth;

public record LoginRequest (
        String login,
        String password
) { }
