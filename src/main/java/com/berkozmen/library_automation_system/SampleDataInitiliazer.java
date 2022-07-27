package com.berkozmen.library_automation_system;


import com.berkozmen.library_automation_system.model.entity.Role;
import com.berkozmen.library_automation_system.model.entity.User;
import com.berkozmen.library_automation_system.repository.RoleRepository;
import com.berkozmen.library_automation_system.repository.UserRepository;
import com.berkozmen.library_automation_system.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class SampleDataInitiliazer implements ApplicationRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @Override
    public void run(ApplicationArguments args) {

        Role adminRole = new Role();
        adminRole.setName("ROLE_ADMIN");
        Role userRole = new Role();
        userRole.setName("ROLE_USER");

        // Creating ADMIN & USER roles
        Role savedAdminRole = null;
        if (!roleRepository.existsByName(adminRole.getName())) {
            savedAdminRole = roleRepository.save(adminRole);
        }
        Role savedUserRole = null;
        if (!roleRepository.existsByName(userRole.getName())) {
            savedUserRole = roleRepository.save(userRole);
        }

        // Creating a sample Admin USER
        User adminUser = new User(null, "admin-user", "admin@mail.com", "pass1234",
                Arrays.asList(savedAdminRole));
        if (!userRepository.existsByUsername(adminUser.getUsername())) {
            userService.signup(adminUser);
        }

        // Creating a sample USER
        User sampleUser = new User(null, "sample-user", "sample@mail.com", "pass1234",
                Arrays.asList(savedUserRole));
        if (!userRepository.existsByUsername(sampleUser.getUsername())) {
            userService.signup(sampleUser);
        }

    }

}
