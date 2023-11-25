package pa.com.erpbar.users;

import pa.com.erpbar.role.Role;

import java.util.Set;

public record UserRecord(Long id, String fullname, String email, Set<Role> roles) {
}
