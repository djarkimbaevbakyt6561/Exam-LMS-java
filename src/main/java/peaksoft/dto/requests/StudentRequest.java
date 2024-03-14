package peaksoft.dto.requests;

import peaksoft.entities.LoginDetails;
import peaksoft.entities.Student;
import peaksoft.enums.Role;
import peaksoft.enums.StudyFormat;

public record StudentRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        Long groupId,
        String studyFormat
) {
    public Student build() {
        Student newStudent = new Student();
        newStudent.setFirstName(this.firstName);
        newStudent.setLastName(this.lastName);
        newStudent.setLoginDetails(LoginDetails.builder().role(Role.STUDENT).email(this.email).password(this.password).build());
        newStudent.setPhoneNumber(this.phoneNumber);
        newStudent.setStudyFormat(StudyFormat.valueOf(this.studyFormat.toUpperCase()));
        return newStudent;
    }
}
