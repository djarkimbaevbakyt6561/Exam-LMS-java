package peaksoft.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import peaksoft.enums.StudyFormat;


@Entity
@Table(name = "students")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Student   {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "students_gen")
    @SequenceGenerator(name = "students_gen", sequenceName = "students_seq", allocationSize = 1)
    private Long id;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    @OneToOne(cascade = {CascadeType.PERSIST, CascadeType.REMOVE, CascadeType.MERGE})
    private LoginDetails loginDetails;
    @Enumerated(EnumType.STRING)
    private StudyFormat studyFormat;
    @ManyToOne
    private Group group;
}
