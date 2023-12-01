package pa.com.erpbar.role;

import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;


@RequiredArgsConstructor
public enum RoleEnum {
    USER(Collections.emptySet()),
            ADMIN(
                    Set.of(
                            PermissionEnum.ADMIN_READ,
                            PermissionEnum.ADMIN_UPDATE,
                            PermissionEnum.ADMIN_DELETE,
                            PermissionEnum.ADMIN_CREATE,
                            PermissionEnum.MANAGER_READ,
                            PermissionEnum.MANAGER_UPDATE,
                            PermissionEnum.MANAGER_DELETE,
                            PermissionEnum.MANAGER_CREATE
                    )
            ),

            MANAGER(
                        Set.of(
                                PermissionEnum.MANAGER_READ,
                                PermissionEnum.MANAGER_UPDATE,
                                PermissionEnum.MANAGER_DELETE,
                                PermissionEnum.MANAGER_CREATE
                     )
            );


    private final Set<PermissionEnum>permissions;

    public Set<PermissionEnum> getPermissions() {
        return permissions;
    }




}
