package pa.com.erpbar.role;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import pa.com.erpbar.users.User;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "users")
public class RoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NaturalId
    private String name;

    @ManyToMany(mappedBy = "roleEntities")
    private Collection<User> users = new HashSet<>();

    public RoleEntity(String name) {
        this.name = name;
    }

    public void removeAllUsersFromRole(){
        if(this.getUsers() != null){
            List<User> usersInRole = this.getUsers().stream().toList();
            usersInRole.forEach(this::removeUserFromRole);
        }

    }

    public void removeUserFromRole(User user) {
        user.getRoleEntities().remove(this);
        this.getUsers().remove(user);
    }

    public void assignUserToRole(User user){
        user.getRoleEntities().add(this);
        this.getUsers().add(user);
    }


}
