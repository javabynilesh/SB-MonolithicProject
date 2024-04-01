package com.mono.entity;

import javax.persistence.Embeddable;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Data
@Embeddable
public class Customers {
	
	//@NotNull(message="First Name Can not be null")
	@NotBlank(message="First name must not be null")
	private String firstName;
	private String middleName;
	//@NotNull(message="Last Name Can not be null")
	@NotBlank(message="Last name must not be null")
	private String lastName;
	
}
