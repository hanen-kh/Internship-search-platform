package where.Entities;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
    private Date dateOfBirth;

    private String university;


}
