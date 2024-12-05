package where.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Entity
@Getter
@Setter
@Inheritance (strategy = InheritanceType.JOINED)
@Table(name = "Utilisateur")
public class User implements Serializable {

    private static final long SerialVersionUID=1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    protected long id;

    protected String firstname;
    protected String lastname;
    protected String email;


    protected String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role_id")
    private  Role role;


}
