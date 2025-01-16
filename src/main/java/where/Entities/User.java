package where.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;

@Entity
@Getter
@Setter
@Inheritance (strategy = InheritanceType.JOINED)
@Table(name = "Utilisateur")
public class User implements Serializable , UserDetails {

    private static final long SerialVersionUID=1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    protected long id;

    protected String firstname;
    protected String lastname;
    @Column(unique = true)
    protected String email;
    protected String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="role_id")
    protected Role role;


    @Override
    public String getPassword() {
        return this.password;
    }
    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_"+this.role.getLibelle()));
    }

}
