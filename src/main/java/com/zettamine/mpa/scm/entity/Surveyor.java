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
@Table(name = "surveyor", schema = "mpa")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Surveyor extends BaseEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "surveyor_id")
	private Integer surveyorId;

	@Column(name = "surveyor_licence_id", unique = true, nullable = false)
	private String surveyorLicenceId;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@Column(name = "email", unique = true)
	private String email;

	@Column(name = "phone", unique = true)
	private String phone;

	@Column(name = "geodetic_control")
	private Boolean geodeticControl;

	@Column(name = "historic_preservation")
	private Boolean historicPreservation;
	
	@ManyToOne
	@JoinColumn(name = "srvy_co_id", nullable = false)
	private SurveyCompany surveyCompany;

}
