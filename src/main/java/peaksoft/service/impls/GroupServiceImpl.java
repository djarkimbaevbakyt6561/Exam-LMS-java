package peaksoft.service.impls;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.requests.GroupRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.GroupResponse;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCountOfStudentsResponse;
import peaksoft.dto.responses.unions.UnionGroupResponse;
import peaksoft.entities.Course;
import peaksoft.entities.Group;
import peaksoft.entities.Student;
import peaksoft.repositories.CourseRepository;
import peaksoft.repositories.GroupRepository;
import peaksoft.service.GroupService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class GroupServiceImpl implements GroupService {
    private final GroupRepository groupRepository;
    private final CourseRepository courseRepository;

    @Override
    public SimpleResponse save(GroupRequest groupRequest) {
        Group group = groupRequest.build();
        groupRepository.save(group);
        return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully saved!").build();
    }

    @Override
    public UnionGroupResponse findById(Long groupId) {
        try {
            Group foundGroup = groupRepository.findById(groupId).orElseThrow(NoSuchElementException::new);
            return UnionGroupResponse.builder()
                    .data(GroupResponse
                            .builder()
                            .id(foundGroup.getId())
                            .groupName(foundGroup.getGroupName())
                            .imageLink(foundGroup.getImageLink())
                            .description(foundGroup.getDescription())
                            .build())
                    .status(SimpleResponse
                            .builder()
                            .httpStatus(HttpStatus.OK)
                            .message("Successfully Returned!")
                            .build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionGroupResponse.builder()
                    .data(null)
                    .status(SimpleResponse
                            .builder()
                            .httpStatus(HttpStatus.NOT_FOUND)
                            .message("Group with id " + groupId + " is not found!")
                            .build())
                    .build();
        }
    }

    @Override
    public SimpleResponse update(Long groupId, GroupRequest groupRequest) {
        try {
            Group foundGroup = groupRepository.findById(groupId).orElseThrow(NoSuchElementException::new);
            foundGroup.setGroupName(groupRequest.groupName());
            foundGroup.setImageLink(groupRequest.imageLink());
            foundGroup.setDescription(groupRequest.description());
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully updated!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse deleteById(Long groupId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(NoSuchElementException::new);
            List<Course> courses = group.getCourses();
            if (courses != null) {
                for (Course course : courses) {
                    course.getGroups().remove(group);
                }
            }
            groupRepository.deleteById(groupId);
            return SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully deleted!").build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message(e.getMessage()).build();
        }
    }

    @Override
    @Transactional
    public SimpleResponse assignGroupToCourse(Long groupId, Long courseId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(NoSuchElementException::new);
            Course course = courseRepository.findById(courseId).orElseThrow(NoSuchElementException::new);

            group.getCourses().add(course);
            course.getGroups().add(group);
            return SimpleResponse.builder().message("Successfully assigned!").httpStatus(HttpStatus.OK).build();
        } catch (NoSuchElementException e) {
            return SimpleResponse.builder().message("Group or Course with given id is not found!").httpStatus(HttpStatus.NOT_FOUND).build();
        }
    }

    @Override
    public UnionCountOfStudentsResponse getCountOfStudentsFromGroup(Long groupId) {
        try {
            Group group = groupRepository.findById(groupId).orElseThrow(NoSuchElementException::new);
            List<Student> students = group.getStudents();
            return UnionCountOfStudentsResponse
                    .builder()
                    .data(CountOfStudentsResponse.builder().count(students.size()).build())
                    .status(SimpleResponse.builder().httpStatus(HttpStatus.OK).message("Successfully returned!").build())
                    .build();
        } catch (NoSuchElementException e) {
            return UnionCountOfStudentsResponse
                    .builder()
                    .data(null)
                    .status(SimpleResponse.builder().httpStatus(HttpStatus.NOT_FOUND).message("Group with id " + groupId + " is not found!").build())
                    .build();
        }
    }
}
