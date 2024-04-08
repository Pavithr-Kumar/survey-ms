package com.zettamine.mpa.scm.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "survey_type", schema = "mpa")

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class SurveyType extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "srvy_type_id")
	private Integer surveyTypeId;

	@Column(name = "srvy_type_name", unique = true, nullable = false)
	private String surveyTypeName;

	@Column(name = "description", columnDefinition = "TEXT")
	private String description;
	
	@ManyToMany()
	@JoinTable(name = "survey_company_survey_type", joinColumns = @JoinColumn(name = "srvy_type_id"),
	inverseJoinColumns = @JoinColumn(name = "srvy_co_id"))
	private List<SurveyCompany> surveyCompanies = new ArrayList<>();

}