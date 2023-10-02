package com.interview.webdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "roles")
@Entity
public class Role extends BaseEntity{
    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private UserRole name;

    @ManyToMany(mappedBy = "roles")
    Set<User> users;

}
