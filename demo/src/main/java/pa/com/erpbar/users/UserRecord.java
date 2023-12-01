package pa.com.erpbar.users;

import pa.com.erpbar.role.RoleEntity;

import java.util.Set;

public record UserRecord(Long id, String fullname, String email, Set<RoleEntity> roleEntities) {
}
