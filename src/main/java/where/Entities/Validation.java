package where.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Validation implements Serializable {
    private static final long SerialVersionUID=1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private Instant expiration;
    private Instant activation;
    private String code;
    @OneToOne(cascade=CascadeType.ALL)
    private User utilisateur;
}
