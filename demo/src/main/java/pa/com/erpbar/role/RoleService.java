package pa.com.erpbar.role;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import pa.com.erpbar.exceptions.RoleAlReadyExistException;
import pa.com.erpbar.users.UserRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleService implements IRoleService{

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role createRole(Role theRole) {
        Optional<Role> checkRole = roleRepository.findByName(theRole.getName());
        if (checkRole.isPresent()){
            throw new RoleAlReadyExistException(checkRole.get().getName() + " role already exists"); //
        }
        return roleRepository.save(theRole);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleRepository.deleteAllById(Collections.singleton(roleId));
    }

    @Override
    public Role findByName(String name) {
        return roleRepository.findByName(name).get();
    }

    @Override
    public Role findById(Long roleId) {
        return roleRepository.findById(roleId).get();
    }

    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {

            role.get().removeUserFromRole(user.get());
            roleRepository.save(role.get());
            return user.get();
        }

       throw new UserNotFoundException("User not found: ");
    }

    @Override
    public Role removeUserFromRole( Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        role.isPresent();
        return roleRepository.save(role.get());
    }



    @Override
    public User assignUserToRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<Role> role = roleRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())) {
            throw new UserAlreadyExistsException(user.get().getFullName()+" is already assigned"+ role.get().getName()+" role");

        }
        role.ifPresent(theRole -> theRole.assignUserToRole(user.get()));
        return user.get();
    }

    @Override
    public Role removeAllUsersFromRole(Long roleId) {
        return null;
    }
}
