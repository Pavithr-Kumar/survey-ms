package com.zettamine.mpa.scm.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mpa.scm.entity.SurveyServiceArea;

public interface SurveyServiceType extends JpaRepository<SurveyServiceArea, Serializable> {

}
