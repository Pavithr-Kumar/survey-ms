package com.zettamine.mpa.scm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "survey_company", schema = "mpa")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class SurveyCompany extends BaseEntity{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "srvy_co_id")
	private Integer surveyCompanyId;
	
	@Column(name = "company_name", unique = true, nullable = false)
	private String companyName;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "city")
	private String city;
	
	@Column(name = "state")
	private String state;
	
	@Column(name = "zipcode")
	private String zipcode;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "phone", unique = true)
	private String phone;
	
	@Column(name = "email", unique = true)
	private String email;
	
	@Column(name = "website")
	private String website;
	
	@Column(name = "notes")
	private String notes;
	
	@Column(name = "triang_survey", nullable = false)
	private Boolean triangularSurvey;
	
	@OneToMany(mappedBy = "surveyCompany", cascade = CascadeType.ALL)
	private List<SurveyServiceArea> serviceAreas = new ArrayList<>();
	
	@OneToMany(mappedBy = "surveyCompany", cascade = CascadeType.ALL)
	private List<Surveyor> surveyors = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(name = "survey_company_survey_type", joinColumns = @JoinColumn(name = "srvy_co_id"),
	inverseJoinColumns = @JoinColumn(name = "srvy_type_id"))
	private List<SurveyType> surveyTypes = new ArrayList<>();
	

}
