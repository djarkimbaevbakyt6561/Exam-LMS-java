package peaksoft.dto.requests;

import peaksoft.entites.Instructor;
import peaksoft.enums.Specialization;

public record InstructorRequest(
        String firstName,
        String lastName,
        String phoneNumber,
        String specialization
) {
    public Instructor build() {
        Instructor newInstructor = new Instructor();
        newInstructor.setFirstName(this.firstName);
        newInstructor.setLastName(this.lastName);
        newInstructor.setPhoneNumber(this.phoneNumber);
        newInstructor.setSpecialization(Specialization.valueOf(this.specialization.toUpperCase()));
        return newInstructor;
    }

}
