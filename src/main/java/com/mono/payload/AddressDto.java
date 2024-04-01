package com.mono.payload;

import java.io.Serializable;

import com.mono.entity.MsmeOrder;

import lombok.Data;

@Data
public class AddressDto implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private Long addrId;
	private String flatNo;
	private String buildingName;
	private Integer pinCode;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	private MsmeOrder msmeOrder;
}
