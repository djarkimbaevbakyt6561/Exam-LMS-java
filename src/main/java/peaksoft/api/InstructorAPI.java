package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.InstructorResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.service.InstructorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructors")
public class InstructorAPI {
    private final InstructorService instructorService;

    @PostMapping("/save")
    SimpleResponse save(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.save(instructorRequest);
    }

    @GetMapping("/{instructorId}")
    public InstructorResponse getById(@PathVariable Long instructorId) {
        return instructorService.findById(instructorId);
    }

    @PutMapping("/{instructorId}")
    public SimpleResponse update(@PathVariable Long instructorId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.update(instructorId, instructorRequest);
    }

    @DeleteMapping("/{instructorId}")
    public SimpleResponse delete(@PathVariable Long instructorId) {
        return instructorService.deleteById(instructorId);
    }

    @PostMapping("/assign/company")
    public SimpleResponse assignInstructorToCompany(@RequestParam("instructorId") Long instructorId, @RequestParam("companyId") Long companyId) {
        return instructorService.assignInstructorToCompany(instructorId, companyId);
    }
    @PostMapping("/assign/course")
    public SimpleResponse assignInstructorToCourse(@RequestParam("instructorId") Long instructorId, @RequestParam("courseId") Long courseId) {
        return instructorService.assignInstructorToCourse(instructorId, courseId);
    }

    @GetMapping("/{instructorId}/count/students")
    public CountOfStudentsResponse getCountOfStudentsFromThatInstructorTeach(@PathVariable Long instructorId){
        return instructorService.getCountOfStudentsFromThatInstructorTeach(instructorId);
    }
}
