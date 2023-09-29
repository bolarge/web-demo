package com.interview.webdemo.service.impl;

import com.interview.webdemo.dataaccess.IdentityRepository;
import com.interview.webdemo.dataaccess.UserRepository;
import com.interview.webdemo.datatransfer.SignUpRequest;
import com.interview.webdemo.datatransfer.UserRecord;
import com.interview.webdemo.domain.Identity;
import com.interview.webdemo.domain.User;
import com.interview.webdemo.service.IdentityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class IdentityServiceImpl implements IdentityService {

    private final IdentityRepository identityRepository;
    private final UserRepository userRepository;
    @Override
    public Identity signUpUser(SignUpRequest signUpRequest) {
        return identityRepository.save(new Identity(signUpRequest.username(), signUpRequest.password(), signUpRequest.email(),
                signUpRequest.dateOfBirth()));
    }

    @Override
    public UserRecord registerUser(UserRecord userRecord, Integer userIdentity) {
        Optional<Identity> queriedIdentity = identityRepository.findById(userIdentity);
        Identity foundIdentity = queriedIdentity.orElseThrow();

        User newUser = User.builder()
                .firstName(userRecord.firstName())
                .lastName(userRecord.lastName())
                .isAdmin(userRecord.isAdmin())
                .identity(foundIdentity)
                .build();
        newUser = userRepository.save(newUser);
        return new UserRecord(newUser.getFirstName(), newUser.getLastName(), newUser.isAdmin(), newUser.getIdentity().getUsername());
    }

    @Override
    @Transactional(readOnly = true)
    public Iterable<User> fetchAllUsers() {
        return userRepository.findAll();
    }
}
