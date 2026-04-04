package pl.dalke.issue_tracker_api.ticket;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TicketRepository extends JpaRepository<TicketEntity, UUID> {
    List<TicketEntity> findByStatus(TicketStatus status);
}