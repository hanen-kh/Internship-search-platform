package where.Entities;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Application implements Serializable {
    private static final long SerialVersionUID=1L;
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Id
    private long id;

    private String candidate_name;
    private String university;
    private String candidate_phone_number;
    private String candidate_address;
    private String candidate_email;
    @OneToOne
    @JoinColumn(name = "candidate_id", nullable = false)
    private  Candidate candidate;

    @ManyToOne
    @JoinColumn(name = "offer_id", nullable = false)
    private Offer jobOffer;
    @Lob
    private byte[] cv;
    @Enumerated(EnumType.STRING)
    private StatusApplication status; // Ex: PENDING, ACCEPTED, REJECTED
    private Date applicationDate;
}
