package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionAllStudentsResponse;
import peaksoft.dto.responses.unions.UnionStudentResponse;
import peaksoft.service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentAPI {
    private final StudentService studentService;

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/save")
    SimpleResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{studentId}")
    public UnionStudentResponse getById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PutMapping("/{studentId}")
    public SimpleResponse update(@PathVariable Long studentId, @RequestBody StudentRequest studentRequest) {
        return studentService.update(studentId, studentRequest);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @DeleteMapping("/{studentId}")
    public SimpleResponse delete(@PathVariable Long studentId) {
        return studentService.deleteById(studentId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @PostMapping("/assign/group")
    public SimpleResponse assignStudentToGroup(@RequestParam("studentId") Long studentId, @RequestParam("groupId") Long groupId){
        return studentService.assignStudentToGroup(studentId, groupId);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/company/students")
    public UnionAllStudentsResponse getAllSortedByStudyFormatStudentsFromCompany(@RequestParam("companyId") Long companyId, @RequestParam("studyFormat") String studyFormat){
        return studentService.getAllSortedByStudyFormatStudentsFromCompany(companyId, studyFormat);
    }
}
