package com.interview.webdemo.datatransfer;

import com.interview.webdemo.util.DateOfBirth;
import com.interview.webdemo.util.PasswordMatching;
import com.interview.webdemo.util.ValidPassword;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@PasswordMatching(
        password = "password",
        confirmPassword = "confirmPassword",
        message = "validation required (not empty, strong password with at least 1 upper case, 1 special\n" +
                "characters: e.g., !@#$%^&*, 1 number and must be minimum of 8 characters)"
)
public record SignUpRequest(@NotEmpty(message = "validation required (not empty, min 4 characters)")
                            @Size(min = 4)
                            String username,
                            @ValidPassword
                            String password,
                            @ValidPassword
                            String confirmPassword,
                            @NotEmpty(message = "validation required (not empty, valid email address)")
                            @Email String email,

                            @NotNull(message = "Date of birth is required.")
                            @DateOfBirth(message = "validation required (not empty, should be 16 years or greater)")
                            @Past(message = "Date of birth must be in the past")
                            Date dateOfBirth) {
}
