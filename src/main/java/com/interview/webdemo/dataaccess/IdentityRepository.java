package com.interview.webdemo.dataaccess;

import com.interview.webdemo.domain.Identity;
import org.springframework.data.repository.CrudRepository;

public interface IdentityRepository extends CrudRepository<Identity, Integer> {
}
