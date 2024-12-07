package where.Entities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@PrimaryKeyJoinColumn(name = "recruiter_id")
public class Recruiter extends User implements Serializable {
    private static final long SerialVersionUID=1L;
    @ManyToOne
    private Company company;
}
