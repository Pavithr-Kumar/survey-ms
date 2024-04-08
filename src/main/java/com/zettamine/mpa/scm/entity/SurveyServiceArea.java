package com.zettamine.mpa.scm.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "survey_service_area", schema = "mpa")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SurveyServiceArea extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "service_area_id")
	private Integer serviceAreaId;
	
	@Column(name = "county")
	private String county;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zipcode")
	private String zipcode;
	
	@ManyToOne
	@JoinColumn(name = "srvy_co_id", nullable = false)
	private SurveyCompany surveyCompany;

}
