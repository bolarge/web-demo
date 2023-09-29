package com.interview.webdemo.service;

import com.interview.webdemo.datatransfer.SignUpRequest;
import com.interview.webdemo.datatransfer.UserRecord;
import com.interview.webdemo.domain.Identity;
import com.interview.webdemo.domain.User;

import java.util.List;

public interface IdentityService {
    Identity signUpUser(SignUpRequest signUpRequest);
    UserRecord registerUser(UserRecord userRecord, Integer userIdentity);
    Iterable<User> fetchAllUsers();

}
