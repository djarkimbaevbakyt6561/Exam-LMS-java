package peaksoft.dto.requests;


public record UpdateCourseRequest(
        String courseName,
        String description
) {
}
