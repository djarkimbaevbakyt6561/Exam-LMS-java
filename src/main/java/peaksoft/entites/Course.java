package peaksoft.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;
import java.util.List;

@Entity
@Table(name = "courses")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "courses_gen")
    @SequenceGenerator(name = "courses_gen", sequenceName = "courses_seq", allocationSize = 1)
    private Long id;
    private String courseName;
    private String description;
    private ZonedDateTime dateOfStart;

    @OneToMany(mappedBy = "course", cascade = CascadeType.REMOVE)
    private List<Lesson> lessons;

    @OneToMany(mappedBy = "course")
    private List<Instructor> instructors;

    @ManyToOne
    private Company company;

    @ManyToMany
    private List<Group> groups;

    @PrePersist
    public void prePersist() {
        this.dateOfStart = ZonedDateTime.now();
    }
}
