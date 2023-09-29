package com.interview.webdemo.dataaccess;

import com.interview.webdemo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
}
