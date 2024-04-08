package com.zettamine.mpa.scm.repository;

import java.io.Serializable;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mpa.scm.entity.SurveyType;

public interface SurveyServiceTypeRepository extends JpaRepository<SurveyType, Serializable> {

	Optional<SurveyType> findBySurveyTypeName(String safeUpperCaseTrim);

}
