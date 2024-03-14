package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "lessons")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "lessons_gen")
    @SequenceGenerator(name = "lessons_gen", sequenceName = "lessons_seq", allocationSize = 1)
    private Long id;
    private String lessonName;
    @OneToMany(mappedBy = "lesson", cascade = {CascadeType.REMOVE, CascadeType.PERSIST})
    private List<Task> tasks;
    @ManyToOne
    private Course course;
}
