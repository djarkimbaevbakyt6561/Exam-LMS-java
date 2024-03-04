package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.StudentRequest;
import peaksoft.dto.responses.SortedByStudyFormatStudentsResponse;
import peaksoft.dto.responses.StudentResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.service.StudentService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/students")
public class StudentAPI {
    private final StudentService studentService;

    @PostMapping("/save")
    SimpleResponse save(@RequestBody StudentRequest studentRequest) {
        return studentService.save(studentRequest);
    }

    @GetMapping("/{studentId}")
    public StudentResponse getById(@PathVariable Long studentId) {
        return studentService.findById(studentId);
    }

    @PutMapping("/{studentId}")
    public SimpleResponse update(@PathVariable Long studentId, @RequestBody StudentRequest studentRequest) {
        return studentService.update(studentId, studentRequest);
    }

    @DeleteMapping("/{studentId}")
    public SimpleResponse delete(@PathVariable Long studentId) {
        return studentService.deleteById(studentId);
    }

    @PostMapping("/assign/group")
    public SimpleResponse assignStudentToGroup(@RequestParam("studentId") Long studentId, @RequestParam("groupId") Long groupId){
        return studentService.assignStudentToGroup(studentId, groupId);
    }

    @GetMapping("/company/students")
    public SortedByStudyFormatStudentsResponse getAllSortedByStudyFormatStudentsFromCompany(@RequestParam("companyId") Long companyId, @RequestParam("studyFormat") String studyFormat){
        return studentService.getAllSortedByStudyFormatStudentsFromCompany(companyId, studyFormat);
    }
}
