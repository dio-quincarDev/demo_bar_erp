package pa.com.erpbar.dto;

import org.springframework.security.core.GrantedAuthority;

public class RoleDto implements GrantedAuthority {

    private Long id;
    private String name;

    @Override
    public String getAuthority() {
        return null;
    }
}
