package pl.dalke.issue_tracker_api.ticket;

import java.util.UUID;

public record CreateTicketRequest(
        String title,
        String description
//        UUID ownerId;
) { }