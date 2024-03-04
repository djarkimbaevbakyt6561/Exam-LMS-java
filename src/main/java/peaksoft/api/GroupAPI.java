package peaksoft.api;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.requests.GroupRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.GroupResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.service.GroupService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/groups")
public class GroupAPI {
    private final GroupService groupService;

    @PostMapping("/save")
    SimpleResponse save(@RequestBody GroupRequest groupRequest) {
        return groupService.save(groupRequest);
    }

    @GetMapping("/{groupId}")
    public GroupResponse getById(@PathVariable Long groupId) {
        return groupService.findById(groupId);
    }

    @PutMapping("/{groupId}")
    public SimpleResponse update(@PathVariable Long groupId, @RequestBody GroupRequest groupRequest) {
        return groupService.update(groupId, groupRequest);
    }

    @DeleteMapping("/{groupId}")
    public SimpleResponse delete(@PathVariable Long groupId) {
        return groupService.deleteById(groupId);
    }

    @PostMapping("/assign/course")
    public SimpleResponse assignGroupToCourse(@RequestParam("groupId") Long groupId, @RequestParam("courseId") Long courseId) {
        return groupService.assignGroupToCourse(groupId, courseId);
    }
    @GetMapping("/{groupId}/count/students")
    public CountOfStudentsResponse getCountOfStudentsFromGroup(@PathVariable Long groupId){
        return groupService.getCountOfStudentsFromGroup(groupId);
    }


}
