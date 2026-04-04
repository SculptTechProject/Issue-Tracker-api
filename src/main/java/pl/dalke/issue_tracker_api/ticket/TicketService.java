package pl.dalke.issue_tracker_api.ticket;

import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

@Service
public class TicketService {

    private final TicketRepository ticketRepository;

    public TicketService(TicketRepository ticketRepository) {
        this.ticketRepository = ticketRepository;
    }

    public TicketResponse createTicket(CreateTicketRequest request) {
        TicketEntity ticket = new TicketEntity();
        ticket.setId(UUID.randomUUID());
        ticket.setTitle(request.title());
        ticket.setDescription(request.description());
        ticket.setStatus(TicketStatus.OPEN);
        ticket.setCreatedAt(Instant.now());

        TicketEntity savedTicket = ticketRepository.save(ticket);
        return mapToResponse(savedTicket);
    }

    public List<TicketResponse> getAllTickets() {
        return ticketRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    public TicketResponse getTicketById(UUID id) {
        TicketEntity ticket = ticketRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ticket not found"));

        return mapToResponse(ticket);
    }

    public List<TicketResponse> getTicketsByStatus(TicketStatus status) {
        return ticketRepository.findByStatus(status)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    private TicketResponse mapToResponse(TicketEntity ticket) {
        return new TicketResponse(
                ticket.getId(),
                ticket.getTitle(),
                ticket.getDescription(),
                ticket.getStatus(),
                ticket.getCreatedAt()
        );
    }
}