package com.interview.webdemo.dataaccess;

import com.interview.webdemo.domain.Identity;
import com.interview.webdemo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findUserByIdentity(Identity identity);
}
