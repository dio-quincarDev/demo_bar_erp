package pa.com.erpbar.role;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IRoleService {

    List<RoleEntity> getAllRoles();

    RoleEntity createRole(RoleEntity theRoleEntity);

    void deleteRole(Long roleId);

    RoleEntity findByName(String  name);

    RoleEntity findById(Long roleId);

    User removeUserFromRole(Long userId, Long roleId);

    RoleEntity removeUserFromRole(Long roleId);

    User assignUserToRole(Long userId, Long roleId);

    RoleEntity removeAllUsersFromRole(Long roleId);


}
