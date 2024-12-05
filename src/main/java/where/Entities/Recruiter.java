package where.Entities;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@PrimaryKeyJoinColumn(name = "recruiter_id")
public class Recruiter extends User implements Serializable {
    private static final long SerialVersionUID=1L;
    @ManyToOne
    private Company company;
}
