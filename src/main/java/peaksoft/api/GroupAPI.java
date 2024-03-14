package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.GroupRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCountOfStudentsResponse;
import peaksoft.dto.responses.unions.UnionGroupResponse;
import peaksoft.service.GroupService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupAPI {
    private final GroupService groupService;

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/save")
    SimpleResponse save(@RequestBody GroupRequest groupRequest) {
        return groupService.save(groupRequest);
    }

    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{groupId}")
    public UnionGroupResponse getById(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/{groupId}")
    public SimpleResponse update(@PathVariable Long groupId, @RequestBody GroupRequest groupRequest) {
        return groupService.update(groupId, groupRequest);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping("/{groupId}")
    public SimpleResponse delete(@PathVariable Long groupId) {
        return groupService.deleteById(groupId);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @PostMapping("/assign/course")
    public SimpleResponse assignGroupToCourse(@RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId) {
        return groupService.assignGroupToCourse(groupId, courseId);
    }
    @PreAuthorize("hasAnyAuthority('ADMIN', 'INSTRUCTOR')")
    @GetMapping("/{groupId}/count/students")
    public UnionCountOfStudentsResponse getCountOfStudentsFromGroup(@PathVariable Long groupId){
        return groupService.getCountOfStudentsFromGroup(groupId);
    }


}
