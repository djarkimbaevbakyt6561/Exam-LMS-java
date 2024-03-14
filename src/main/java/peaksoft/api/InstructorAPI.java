package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.InstructorRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCountOfStudentsResponse;
import peaksoft.dto.responses.unions.UnionInstructorResponse;
import peaksoft.service.InstructorService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/instructors")
public class InstructorAPI {
    private final InstructorService instructorService;
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    SimpleResponse save(@RequestBody InstructorRequest instructorRequest) {
        return instructorService.save(instructorRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/{instructorId}")
    public UnionInstructorResponse getById(@PathVariable Long instructorId) {
        return instructorService.findById(instructorId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{instructorId}")
    public SimpleResponse update(@PathVariable Long instructorId, @RequestBody InstructorRequest instructorRequest) {
        return instructorService.update(instructorId, instructorRequest);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{instructorId}")
    public SimpleResponse delete(@PathVariable Long instructorId) {
        return instructorService.deleteById(instructorId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign/company")
    public SimpleResponse assignInstructorToCompany(@RequestParam("instructorId") Long instructorId, @RequestParam("companyId") Long companyId) {
        return instructorService.assignInstructorToCompany(instructorId, companyId);
    }
    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign/course")
    public SimpleResponse assignInstructorToCourse(@RequestParam("instructorId") Long instructorId, @RequestParam("courseId") Long courseId) {
        return instructorService.assignInstructorToCourse(instructorId, courseId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{instructorId}/count/students")
    public UnionCountOfStudentsResponse getCountOfStudentsFromThatInstructorTeach(@PathVariable Long instructorId){
        return instructorService.getCountOfStudentsFromThatInstructorTeach(instructorId);
    }
}
