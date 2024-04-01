package com.mono.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data
@Entity
@Getter
@Setter
//@ToString
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addrId;
	private String flatNo;
	private String buildingName;
	private Integer pinCode;
	private Integer cityId;
	private Integer stateId;
	private Integer countryId;
	
	@ManyToOne(targetEntity = MsmeOrder.class,cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORDER_ID",referencedColumnName = "ordid")
	@JsonIgnore
	private MsmeOrder msmeOrder; //bidirectional approach

	
	@Override
	public String toString() {
		return "Address [addrId=" + addrId + ", flatNo=" + flatNo + ", buildingName=" + buildingName + ", pinCode="
				+ pinCode + ", cityId=" + cityId + ", stateId=" + stateId + ", countryId=" + countryId + "]";
	}
		
}
