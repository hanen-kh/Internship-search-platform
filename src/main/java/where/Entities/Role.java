package where.Entities;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.io.Serializable;

@Entity
public class Role implements Serializable {
    private static final long SerialVersionUID=1L;
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private long id;
    @Enumerated(EnumType.STRING)
    private TypeRole libelle;
}
