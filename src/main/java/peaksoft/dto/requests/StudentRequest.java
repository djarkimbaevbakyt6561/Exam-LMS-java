package peaksoft.dto.requests;

import peaksoft.entites.Student;
import peaksoft.enums.StudyFormat;

public record StudentRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        Long groupId,
        String studyFormat
) {
    public Student build() {
        Student newStudent = new Student();
        newStudent.setFirstName(this.firstName);
        newStudent.setLastName(this.lastName);
        newStudent.setEmail(this.email);
        newStudent.setPhoneNumber(this.phoneNumber);
        newStudent.setStudyFormat(StudyFormat.valueOf(this.studyFormat.toUpperCase()));
        return newStudent;
    }
}
