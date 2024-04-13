package com.zettamine.mpa.scm.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zettamine.mpa.scm.dto.SurveyCompanyDto;
import com.zettamine.mpa.scm.dto.SurveyCompanySearchCriteriaDto;
import com.zettamine.mpa.scm.util.SurveyUtility;

import lombok.AllArgsConstructor;

@Repository
public class SurveySearchCriteriaImpl implements ISurveyCompanySearchCriteriaRepository {
	private JdbcTemplate jdbcTemplate;
	
	
	public SurveySearchCriteriaImpl(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
		
	}



	@Override
	public List<SurveyCompanyDto> searchCompaniesByCriteria(SurveyCompanySearchCriteriaDto searchCriteria) {
		 
	        List<Object> queryParams = new ArrayList<>();
	        StringBuilder sql = new StringBuilder("SELECT distinct(m.srvy_co_id), m.company_name, m.state, m.city, m.address, m.email, m.phone, m.zipcode, m.website, m.triang_survey FROM mpa.survey_company m INNER JOIN mpa.survey_service_area a ON m.srvy_co_id = m.srvy_co_id WHERE 1=1");

	        if (searchCriteria.getName() != null) {
	            sql.append(" AND UPPER(m.company_name) ILIKE ?");
	            queryParams.add("%" + SurveyUtility.safeUpperCaseTrim(searchCriteria.getName()) + "%");
	        }
	        

	        if (searchCriteria.getState() != null) {
	            sql.append(" AND UPPER(a.state) = ?");
	            queryParams.add(SurveyUtility.safeUpperCaseTrim(searchCriteria.getState()));
	        }
	        if (searchCriteria.getCity() != null) {
	            sql.append(" AND UPPER(a.city) = ?");
	            queryParams.add(SurveyUtility.safeUpperCaseTrim(searchCriteria.getCity()));
	        }
	        if (searchCriteria.getZipcode() != null) {
	            sql.append(" AND UPPER(a.zipcode) = ?");
	            queryParams.add(SurveyUtility.safeUpperCaseTrim(searchCriteria.getZipcode()));
	        }
	        
	        

	        List<SurveyCompanyDto> companySearchResults = jdbcTemplate.query(sql.toString(), (rs, rowNum) -> {
	            SurveyCompanyDto surveyCompany = new SurveyCompanyDto();
	            surveyCompany.setSurveyCompanyId(rs.getInt("srvy_co_id"));
	            surveyCompany.setCompanyName(rs.getString("company_name"));
	            surveyCompany.setState(rs.getString("state"));
	            surveyCompany.setCity(rs.getString("city"));
	            surveyCompany.setZipcode(rs.getString("zipcode"));
	            surveyCompany.setAddress(rs.getString("address"));
	            surveyCompany.setEmail(rs.getString("email"));
	            surveyCompany.setPhone(rs.getString("phone"));
	            surveyCompany.setWebsite(rs.getString("website"));
	            surveyCompany.setProvidesTriangulationService(rs.getBoolean("triang_survey"));
	            

	            return surveyCompany;
	        }, queryParams.toArray());

	        return companySearchResults;
	    }

	

}
