package com.mono.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class StateNew {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long stateId;
	private String stateName;
	
	@ManyToOne(targetEntity = CountryNew.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name="country_id", referencedColumnName = "countryId")
	@JsonIgnore
	private CountryNew countryNew;
	
	@OneToMany(mappedBy = "stateNew", targetEntity = CityNew.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY )
	@JsonIgnore
	private List<CityNew> cityNews;
	
}
