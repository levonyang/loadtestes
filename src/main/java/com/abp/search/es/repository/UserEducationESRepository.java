package com.abp.search.es.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.abp.search.es.model.UserEducationDAO;

@Repository
public interface UserEducationESRepository extends ElasticsearchRepository<UserEducationDAO, String> {
	
	List<UserEducationDAO> findByUserid(String userid);

}
