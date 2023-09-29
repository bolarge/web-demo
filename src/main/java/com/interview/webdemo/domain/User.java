package com.interview.webdemo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.Objects;
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users")
public class User extends BaseEntity {

	@NotEmpty
	private String firstName;
	@NotEmpty
	private String lastName;
	private boolean isEnabled = false;
	private boolean isAdmin = false;

	@OneToOne
	private Identity identity;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof User user)) return false;
		if (!super.equals(o)) return false;
		return Objects.equals(getFirstName(), user.getFirstName()) && Objects.equals(getLastName(), user.getLastName());
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), getFirstName(), getLastName());
	}
}
