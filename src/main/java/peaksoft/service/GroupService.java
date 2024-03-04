package peaksoft.service;

import peaksoft.dto.requests.GroupRequest;
import peaksoft.dto.responses.CountOfStudentsResponse;
import peaksoft.dto.responses.GroupResponse;
import peaksoft.dto.responses.SimpleResponse;

public interface GroupService {
    SimpleResponse save(GroupRequest groupRequest);

    GroupResponse findById(Long groupId);

    SimpleResponse update(Long groupId, GroupRequest groupRequest);

    SimpleResponse deleteById(Long groupId);

    SimpleResponse assignGroupToCourse(Long groupId, Long courseId);
    CountOfStudentsResponse getCountOfStudentsFromGroup(Long groupId);
}
