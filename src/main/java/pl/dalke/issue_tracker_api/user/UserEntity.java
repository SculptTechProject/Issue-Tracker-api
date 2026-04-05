package pl.dalke.issue_tracker_api.user;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import pl.dalke.issue_tracker_api.ticket.TicketEntity;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    private UUID id;

    @Column(nullable = false, length = 20)
    private String login;

    @Column(nullable = false)
    private String passwordHash;

    @Column()
    private UserRole role = UserRole.USER;

    @OneToMany(mappedBy = "owner")
    private List<TicketEntity> tickets;

    protected UserEntity() {}
}
