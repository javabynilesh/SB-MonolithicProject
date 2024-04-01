package com.mono.payload;

import java.sql.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import com.mono.entity.Customers;

import lombok.Data;

@Data
public class MsmeOrderDto {
	private Long ordId;
	private Customers customer;
	@NotNull(message="please enter age.")
	private int age;
	@NotNull(message="please enter mobile no.")
	private long mobileNo; //dont take int or Integer because throws rage exception
	
	private String email;
	private String ifscCode;
	private String selCategory;//for static dropdown
	private Date dob;
	private List<AddressDto> address; //observe here address -> addressDto
	private int userId;
}
