package pl.dalke.issue_tracker_api.ticket;

import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping
    public TicketResponse createTicket(@RequestBody CreateTicketRequest request, Authentication authentication) {
        String login = authentication.getName();
        return ticketService.createTicket(request, login);
    }

    @GetMapping
    public List<TicketResponse> getAllTickets() {
        return ticketService.getAllTickets();
    }

    @GetMapping("/status")
    public List<TicketResponse> getTicketsByStatus(@RequestParam TicketStatus status) {
        return ticketService.getTicketsByStatus(status);
    }

    @GetMapping("/{id}")
    public TicketResponse getTicketById(@PathVariable UUID id) {
        return ticketService.getTicketById(id);
    }
}