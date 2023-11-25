package pa.com.erpbar.role;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NaturalId;
import org.springframework.security.core.GrantedAuthority;
import pa.com.erpbar.users.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "users")
public class Role  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "roles")
    private Collection<User> users = new HashSet<>();

    public Role(String name) {
        this.name = name;
    }

    public void removeAllUsersFromRole(){
        if(this.getUsers() != null){
            List<User> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromRole);
        }

    }

    public void removeUserFromRole(User user) {
        user.getRoles().remove(this);
        this.getUsers().remove(user);
    }

    public void assignUserToRole(User user){
        user.getRoles().add(this);
        this.getUsers().add(user);
    }


}
