package codesquad.domain;

import codesquad.exception.PermissionVerificationException;
import codesquad.exception.UserVerificationException;

import java.util.Arrays;

public enum UserPermissions {
    ADMIN(0),
    NORMAL(1);

    private long permissions;

    UserPermissions(long permissions) {
        this.permissions = permissions;
    }

    public UserPermissions valueOf(long permissions) {
        return Arrays.stream(UserPermissions.values())
                .filter(p -> p.permissions == permissions)
                .findFirst()
                .orElseThrow(() -> new PermissionVerificationException("권한이 없습니다."));
        // TODO: Exception 처리 잘 해봐요 ㅎ_ㅎ
    }

    public long getPermissions() {
        return permissions;
    }
}
