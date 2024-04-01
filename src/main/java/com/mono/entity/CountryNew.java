package com.mono.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class CountryNew {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long countryId;
	private String countryName;
	
	//bidirectional one to many
	@OneToMany(mappedBy = "countryNew", targetEntity = StateNew.class,cascade = CascadeType.ALL,fetch = FetchType.LAZY)
	@JsonIgnore
	private List<StateNew> stateNew;
	
}
