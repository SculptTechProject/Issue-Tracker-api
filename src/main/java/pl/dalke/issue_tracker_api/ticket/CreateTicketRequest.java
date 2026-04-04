package pl.dalke.issue_tracker_api.ticket;

public record CreateTicketRequest(
        String title,
        String description
) { }