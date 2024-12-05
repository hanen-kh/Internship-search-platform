package where.Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Company implements Serializable {
    private static final long SerialVersionUID=1L;
    @Id
    private long id;
    private String name;
    private String description;
    private String url;
    private String location;
    @OneToMany
    private List<Recruiter> recruiters= new ArrayList<>();
}
