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

@Entity
@Getter
@Setter
@ToString
public class CityNew {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cityId;
	private String cityName;
	
	@ManyToOne(targetEntity = StateNew.class, cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JoinColumn(name = "state_id" , referencedColumnName = "stateId")
	@JsonIgnore
	private StateNew stateNew;
	
}
