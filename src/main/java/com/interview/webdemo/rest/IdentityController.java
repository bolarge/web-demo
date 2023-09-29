package com.interview.webdemo.rest;

import com.interview.webdemo.datatransfer.SignUpRequest;
import com.interview.webdemo.service.IdentityService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@Tag(name = "Identity", description = "Identity Resource")
@RequestMapping("v1/identity")
//@CrossOrigin(origins = "*")
@RestController
public class IdentityController {

    private final IdentityService identityService;
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUpNewUser(@Valid @RequestBody SignUpRequest signUpRequest){
        var requestResponse = identityService.signUpUser(signUpRequest);
        return new ResponseEntity<>(requestResponse, HttpStatus.OK);
    }
}
