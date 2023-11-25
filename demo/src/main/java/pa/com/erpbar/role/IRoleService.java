package pa.com.erpbar.role;

import org.springframework.security.core.userdetails.User;

import java.util.List;

public interface IRoleService {

    List<Role> getAllRoles();

    Role createRole(Role theRole);

    void deleteRole(Long roleId);

    Role findByName(String  name);

    Role findById(Long roleId);

    User removeUserFromRole(Long userId, Long roleId);

    Role removeUserFromRole(Long roleId);

    User assignUserToRole(Long userId, Long roleId);

    Role removeAllUsersFromRole(Long roleId);


}
