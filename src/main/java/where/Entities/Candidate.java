package where.Entities;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@PrimaryKeyJoinColumn(name = "candidate_id")
@Entity
public class Candidate extends User implements Serializable {
    private static final long SerialVersionUID=1L;
    private Date dateOfBirth;
    private String University;


}
