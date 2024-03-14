package peaksoft.dto.requests;

import peaksoft.entities.Instructor;
import peaksoft.entities.LoginDetails;
import peaksoft.enums.Role;
import peaksoft.enums.Specialization;

public record InstructorRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String email,
        String password,
        String specialization
) {
    public Instructor build() {
        Instructor newInstructor = new Instructor();
        newInstructor.setFirstName(this.firstName);
        newInstructor.setLastName(this.lastName);
        newInstructor.setLoginDetails(LoginDetails.builder().role(Role.INSTRUCTOR).email(this.email).password(this.password).build());
        newInstructor.setPhoneNumber(this.phoneNumber);
        newInstructor.setSpecialization(Specialization.valueOf(this.specialization.toUpperCase()));
        return newInstructor;
    }

}
