package com.mono.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mono.payload.MsmeOrderDto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//@Data
@Entity
@Table(name="tbl_order")
@Getter
@Setter
//@ToString
public class MsmeOrder {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ordid")
	private Long ordId;
	@Embedded
	private Customers customer;
	private int age;
	private long mobileNo;
	private String email;
	private String ifscCode;
	private String selCategory;//for static dropdown
	private Date dob;
	
	@OneToMany(targetEntity = Address.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name="ORDER_ID",referencedColumnName = "ORDID")
	
	//bidirectional approach
	//@OneToMany(mappedBy = "msmeOrder",orphanRemoval = true, targetEntity = Address.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	//@JsonIgnore //no needed
	private List<Address> address; //association mapping so dont use @Data annotation
	
	@Column(name="userId", nullable = false)
	private int userId;

	
	
	@Override
	public String toString() {
		return "MsmeOrder [ordId=" + ordId + ", customer=" + customer + ", age=" + age + ", mobileNo=" + mobileNo
				+ ", email=" + email + ", ifscCode=" + ifscCode + ", selCategory=" + selCategory + ", dob=" + dob
				+ ", userId=" + userId + "]";
	}
	
		
}
