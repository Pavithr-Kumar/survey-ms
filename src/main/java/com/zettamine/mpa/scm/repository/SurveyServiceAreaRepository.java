package com.zettamine.mpa.scm.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mpa.scm.entity.SurveyServiceArea;

/**
 * Repository interface for accessing SurveyServiceArea entities.
 */
public interface SurveyServiceAreaRepository extends JpaRepository<SurveyServiceArea, Serializable> {

}
