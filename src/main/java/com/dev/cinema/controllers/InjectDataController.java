package com.dev.cinema.controllers;

import com.dev.cinema.model.Role;
import com.dev.cinema.model.RoleName;
import com.dev.cinema.model.User;
import com.dev.cinema.service.RoleService;
import com.dev.cinema.service.UserService;
import java.util.Set;
import javax.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class InjectDataController {
    private final UserService userService;
    private final RoleService roleService;

    @PostConstruct
    public void injectData() {
        Role adminRole = new Role();
        adminRole.setRoleName(RoleName.ADMIN);
        Role userRole = new Role();
        userRole.setRoleName(RoleName.USER);
        roleService.add(adminRole);
        roleService.add(userRole);
        User admin = new User();
        admin.setEmail("admin");
        admin.setPassword("admin");
        admin.setRoles(Set.of(roleService.getRoleByName("ADMIN")));
        userService.add(admin);
        User user = new User();
        user.setEmail("user@com.ua");
        user.setPassword("123");
        user.setRoles(Set.of(roleService.getRoleByName("USER")));
        userService.add(user);
    }
}
