package peaksoft.dto.requests;


import peaksoft.entites.Group;

public record GroupRequest(
        String groupName,
        String imageLink,
        String description
) {
    public Group build() {
        Group newGroup = new Group();
        newGroup.setGroupName(this.groupName);
        newGroup.setImageLink(this.imageLink);
        newGroup.setDescription(this.description);
        return newGroup;
    }
}
