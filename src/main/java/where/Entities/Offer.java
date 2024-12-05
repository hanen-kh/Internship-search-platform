package where.Entities;

import jakarta.persistence.*;
import lombok.*;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Offer implements Serializable {
    private static final long SerialVersionUID=1L;
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;

    private String title;
    private String description;
    private String location;
    private Date startDate;
    private Date endDate;
    @OneToMany(mappedBy = "jobOffer", cascade = CascadeType.ALL)
    private List<Application> applications=new ArrayList<>();
}
