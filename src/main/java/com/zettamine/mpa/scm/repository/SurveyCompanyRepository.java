package com.zettamine.mpa.scm.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mpa.scm.entity.SurveyCompany;

public interface SurveyCompanyRepository extends JpaRepository<SurveyCompany, Serializable> {

	Optional<SurveyCompany> findByCompanyName(String companyName);
	
	Optional<SurveyCompany> findByPhone(String mobileNumber);
	
	Optional<SurveyCompany> findByEmail(String email);


}
