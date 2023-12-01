package pa.com.erpbar.role;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
    @Autowired
    private final RoleEntityRepository roleEntityRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public List<RoleEntity> getAllRoles() {
        return roleEntityRepository.findAll();
    }

    @Override
    public RoleEntity createRole(RoleEntity theRoleEntity) {
        Optional<RoleEntity> checkRole = roleEntityRepository.findByName(theRoleEntity.getName());
        if (checkRole.isPresent()){
            throw new RoleAlReadyExistException(checkRole.get().getName() + " role already exists"); //
        }
        return roleEntityRepository.save(theRoleEntity);
    }

    @Override
    public void deleteRole(Long roleId) {
        this.removeAllUsersFromRole(roleId);
        roleEntityRepository.deleteAllById(roleId);
        deleteAllById
    }

    @Override
    public RoleEntity findById(Long roleId) {
        return roleEntityRepository.findById(roleId).get();
    }

    @Override
    public RoleEntity findByName(String name) {
        return roleEntityRepository.findByName(name).get();
    }



    @Override
    public User removeUserFromRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<RoleEntity> role = roleEntityRepository.findById(roleId);
        if (role.isPresent() && role.get().getUsers().contains(user.get())) {

            role.get().removeUserFromRole(user.get());
            roleEntityRepository.save(role.get());
            return user.get();
        }

       throw new UserNotFoundException("User not found: ");
    }

    @Override
    public RoleEntity removeUserFromRole(Long roleId) {
        Optional<RoleEntity> role = roleEntityRepository.findById(roleId);
        role.isPresent();
        return roleEntityRepository.save(role.get());
    }



    @Override
    public User assignUserToRole(Long userId, Long roleId) {
        Optional<User> user = userRepository.findById(userId);
        Optional<RoleEntity> role = roleEntityRepository.findById(roleId);
        if (user.isPresent() && user.get().getRoles().contains(role.get())) {
            throw new UserAlreadyExistsException(user.get().getFullName()+" is already assigned"+ role.get().getName()+" role");

        }
        role.ifPresent(theRole -> theRole.assignUserToRole(user.get()));
        return user.get();
    }

    @Override
    public RoleEntity removeAllUsersFromRole(Long roleId) {
        return null;
    }
}
