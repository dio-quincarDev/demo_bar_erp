package pa.com.erpbar.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;
import pa.com.erpbar.role.IRoleService;
import pa.com.erpbar.role.Role;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final IRoleService roleService;

    @GetMapping("/all")
    public ResponseEntity<List<Role>> getAllRoles() {
        return new ResponseEntity<>(roleService.getAllRoles(), FOUND);
    }

    @PostMapping("/create")
    public ResponseEntity<Role> createRole(@RequestBody Role role) {
        return new ResponseEntity<>(roleService.createRole(role), CREATED);
    }

    @DeleteMapping("/delete/{id}")
    public void createRole(@PathVariable("id") Long roleId) {
        roleService.deleteRole(roleId);
    }

    @PostMapping("/remove-all-users-from-role/{id}")
    public Role removeAllUsersfromRole(@PathVariable("id") Long roleId) {
       return roleService.removeAllUsersFromRole(roleId);
    }

    @PostMapping("/remove-user-from-role")
    public User removeUserFromRole(@RequestParam("userId") Long userId,
                                   @RequestParam("roleId") Long roleId) {
            return roleService.removeUserFromRole(userId, roleId);

    }

    @PostMapping("/assign-user-to-role")
    public User assignUserToRole(@RequestParam("userId") Long userId,
                                   @RequestParam("roleId") Long roleId) {
        return roleService.assignUserToRole(userId, roleId);

    }
}
