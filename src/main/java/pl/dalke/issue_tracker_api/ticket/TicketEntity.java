package pl.dalke.issue_tracker_api.ticket;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.dalke.issue_tracker_api.user.UserEntity;

import java.time.Instant;
import java.util.UUID;

@Setter
@Getter
@Entity
@Table(name = "tickets")
public class TicketEntity {

    @Id
    private UUID id;

    @Column(nullable = false, length = 200)
    private String title;

    @Column(nullable = false, length = 2000)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 30)
    private TicketStatus status;

    @Column(nullable = false, updatable = false)
    private Instant createdAt;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    protected TicketEntity() {}

}