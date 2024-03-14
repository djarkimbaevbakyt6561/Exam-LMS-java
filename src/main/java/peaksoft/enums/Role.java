package peaksoft.enums;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority {
    INSTRUCTOR,
    STUDENT,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}
