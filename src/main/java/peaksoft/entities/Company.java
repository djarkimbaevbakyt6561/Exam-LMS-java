package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "companies")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "companies_gen")
    @SequenceGenerator(name = "companies_gen", sequenceName = "companies_seq", allocationSize = 1)
    private Long id;
    @Column(unique = true)
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
    @ManyToMany(mappedBy = "companies")
    private List<Instructor> instructors;
    @OneToMany(mappedBy = "company", cascade = CascadeType.REMOVE)
    private List<Course> courses;
}
