package com.zettamine.mpa.scm.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.zettamine.mpa.scm.entity.Surveyor;

/**
 * Repository interface for accessing Surveyor entities.
 */
public interface SurveyorRepository extends JpaRepository<Surveyor, Serializable> {

    /**
     * Finds Surveyors by their license ID, email, and phone number.
     *
     * @param surveyorLicenceId The license ID of the surveyor.
     * @param email The email address of the surveyor.
     * @param phoneNo The phone number of the surveyor.
     * @return List of Surveyors matching the given criteria.
     */
    List<Surveyor> findBySurveyorLicenceIdAndEmailAndPhone(String surveyorLicenceId,
                                                           String email,
                                                           String phoneNo);

    /**
     * Finds a Surveyor by their license ID.
     *
     * @param Id The license ID of the surveyor.
     * @return An optional containing the Surveyor if found, otherwise empty.
     */
    Optional<Surveyor> findBySurveyorLicenceId(String Id);

    /**
     * Finds a Surveyor by their email address.
     *
     * @param email The email address of the surveyor.
     * @return An optional containing the Surveyor if found, otherwise empty.
     */
    Optional<Surveyor> findByEmail(String email);

    /**
     * Finds a Surveyor by their phone number.
     *
     * @param phone The phone number of the surveyor.
     * @return An optional containing the Surveyor if found, otherwise empty.
     */
    Optional<Surveyor> findByPhone(String phone);

    /**
     * Finds Surveyors by their license ID, email, or phone number.
     *
     * @param upperCase The license ID.
     * @param upperCase2 The email address.
     * @param upperCase3 The phone number.
     * @return List of Surveyors matching the given criteria.
     */
    List<Surveyor> findBySurveyorLicenceIdOrEmailOrPhone(String upperCase, String upperCase2, String upperCase3);

}
