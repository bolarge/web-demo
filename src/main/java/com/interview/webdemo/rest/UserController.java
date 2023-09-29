package com.interview.webdemo.rest;

import com.interview.webdemo.datatransfer.UserRecord;
import com.interview.webdemo.service.IdentityService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("v1")
@RestController
public class UserController {

    private final IdentityService identityService;
    @PostMapping("/users/{userIdentity}")
    public ResponseEntity<UserRecord> registerUser(@Valid @RequestBody UserRecord userRecord, @PathVariable Integer userIdentity){
        UserRecord newRecord = identityService.registerUser(userRecord, userIdentity);
        return new ResponseEntity<>(newRecord, HttpStatus.CREATED);
    }
    @GetMapping("/users")
    public ResponseEntity<?> getAllUsers(){
        return new ResponseEntity<>(identityService.fetchAllUsers(), HttpStatus.OK);
    }
}
