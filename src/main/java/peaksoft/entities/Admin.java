package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "admins")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Builder
@Setter
public class Admin {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "admins_gen")
    @SequenceGenerator(name = "admins_gen", sequenceName = "admins_seq", allocationSize = 1)
    private Long id;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private LoginDetails loginDetails;

}
