package pl.dalke.issue_tracker_api.ticket;

import java.time.Instant;
import java.util.UUID;

public record TicketResponse(
        UUID id,
        String title,
        String description,
        TicketStatus status,
        Instant createdAt
) { }