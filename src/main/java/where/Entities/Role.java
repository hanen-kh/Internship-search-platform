package where.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Role implements Serializable {
    private static final long SerialVersionUID=1L;
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeRole libelle;
}
