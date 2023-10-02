package com.interview.webdemo.service.impl;

import com.interview.webdemo.dataaccess.IdentityRepository;
import com.interview.webdemo.dataaccess.RoleRepository;
import com.interview.webdemo.dataaccess.UserRepository;
import com.interview.webdemo.datatransfer.SignUpRequest;
import com.interview.webdemo.datatransfer.UserRecord;
import com.interview.webdemo.domain.Identity;
import com.interview.webdemo.domain.Role;
import com.interview.webdemo.domain.User;
import com.interview.webdemo.domain.UserRole;
import com.interview.webdemo.service.IdentityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
@Slf4j
@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository identityRepository;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder encoder;
    @Override
    public Identity signUpUser(SignUpRequest signUpRequest) {
        return identityRepository.save(new Identity(signUpRequest.username(), encoder.encode(signUpRequest.password()),
                signUpRequest.email(), signUpRequest.dateOfBirth()));
    }

    @Override
    public UserRecord createUserProfile(UserRecord userRecord, Integer userIdentity) {
        Optional<Identity> queriedIdentity = identityRepository.findById(userIdentity);
        Identity foundIdentity = queriedIdentity.orElseThrow();

        Set<String> strRoles = userRecord.userRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Role adminRole = roleRepository.findByName(UserRole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);
                        break;
                    case "hr_admin":
                        Role modRole = roleRepository.findByName(UserRole.ROLE_HR_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    default:
                        Role userRole = roleRepository.findByName(UserRole.ROLE_USER)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        User newUser = new User(userRecord.firstName(),userRecord.lastName(),userRecord.isAdmin(),foundIdentity, roles);
        newUser = userRepository.save(newUser);

        return new UserRecord(newUser.getFirstName(), newUser.getLastName(), newUser.isAdmin(),
                strRoles, newUser.getIdentity().getUsername());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
