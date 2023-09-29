package com.interview.webdemo.datatransfer;

import com.interview.webdemo.domain.Identity;
import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.NotEmpty;

public record UserRecord(@NotEmpty(message = "validation required (not empty, min 3 characters)")
                         String firstName,
                         @NotEmpty(message = "validation required (not empty, min 3 characters)")
                         String lastName,
                         @AssertTrue boolean isAdmin,
                         String username) {
}
