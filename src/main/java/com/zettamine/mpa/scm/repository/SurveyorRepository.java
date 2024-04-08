package com.zettamine.mpa.scm.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mpa.scm.entity.Surveyor;

public interface SurveyorRepository extends JpaRepository<Surveyor, Serializable> {
	List<Surveyor> findBySurveyorLicenceIdAndEmailAndPhone(String surveyorLicenceId,
            String email,
            String phoneNo);
	
	Optional<Surveyor> findBySurveyorLicenceId(String Id);
	
	Optional<Surveyor> findByEmail(String email);
	
	Optional<Surveyor> findByPhone(String phone);

	List<Surveyor> findBySurveyorLicenceIdOrEmailOrPhone(String upperCase, String upperCase2, String upperCase3);

}
