package peaksoft.dto.requests;

import peaksoft.entites.Course;

public record CourseRequest(
        String courseName,
        String description,
        Long companyId
) {
    public Course build() {
        Course newCourse = new Course();
        newCourse.setCourseName(this.courseName);
        newCourse.setDescription(this.description);
        return newCourse;
    }
}
