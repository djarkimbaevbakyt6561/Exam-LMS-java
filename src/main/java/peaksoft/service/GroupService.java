package peaksoft.service;

import peaksoft.dto.requests.GroupRequest;
import peaksoft.dto.responses.SimpleResponse;
import peaksoft.dto.responses.unions.UnionCountOfStudentsResponse;
import peaksoft.dto.responses.unions.UnionGroupResponse;

public interface GroupService {
    SimpleResponse save(GroupRequest groupRequest);

    UnionGroupResponse findById(Long groupId);

    SimpleResponse update(Long groupId, GroupRequest groupRequest);

    SimpleResponse deleteById(Long groupId);

    SimpleResponse assignGroupToCourse(Long groupId, Long courseId);
    UnionCountOfStudentsResponse getCountOfStudentsFromGroup(Long groupId);
}
