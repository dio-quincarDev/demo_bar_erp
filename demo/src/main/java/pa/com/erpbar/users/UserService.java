package pa.com.erpbar.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pa.com.erpbar.role.RoleEntity;
import pa.com.erpbar.role.RoleEntityRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
public class UserService  {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleEntityRepository roleEntityRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    // Method to create a new user with a default role
    public User createUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        RoleEntity defaultRoleEntity = roleEntityRepository.findByRoleName("USER");
        user.getRoleEntities().add(defaultRoleEntity);
        return userRepository.save(user);
    }

    // Method to find a user by email
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Method to find all users
    public List<User> findAll() {
        return userRepository.findAll();
    }

    // Method to update a user's roles
    public User updateRoles(User user, List<RoleEntity> roleEntities) {
        user.setRoleEntities(new HashSet<>(roleEntities));
        return userRepository.save(user);
    }

    // Method to delete a user by id
    public void deleteUserById(Long id) {
        userRepository.deleteById((long) Math.toIntExact(id));
    }
}

