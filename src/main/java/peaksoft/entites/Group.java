package peaksoft.entites;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "groups")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "groups_gen")
    @SequenceGenerator(name = "groups_gen", sequenceName = "groups_seq", allocationSize = 1)
    private Long id;
    private String groupName;
    private String imageLink;
    private String description;
    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST, CascadeType.REMOVE})
    private List<Student> students;
    @ManyToMany(mappedBy = "groups")
    private List<Course> courses;
}
