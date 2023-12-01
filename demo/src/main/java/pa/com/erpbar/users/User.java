package pa.com.erpbar.users;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import pa.com.erpbar.role.RoleEntity;
import pa.com.erpbar.role.RoleEnum;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;


@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = "email"))
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String fullname;

    @Column(unique = true)
    private String email;

    private String password;

    private Collection<RoleEnum> roles = new HashSet<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER,
            cascade = {CascadeType.PERSIST,
                    CascadeType.MERGE, CascadeType.DETACH})
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "role_id", referencedColumnName = "id"))
    private Collection<RoleEntity> roleEntities = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (RoleEnum role : roles) {
            SimpleGrantedAuthority authority = new SimpleGrantedAuthority(role.name());
            authorities.add(authority);
        }
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    @JsonIgnore
    public Collection<RoleEntity> getRoleEntities() {
        return roleEntities;
    }

    public void setRolesFromRoleEntities() {
        for (RoleEntity roleEntity : roleEntities) {
            String roleName = roleEntity.getName();
            RoleEnum roleEnum = RoleEnum.valueOf(roleName);
            roles.add(roleEnum);
        }
    }
}