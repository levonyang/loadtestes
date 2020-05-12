package com.abp.demo.es.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.abp.demo.es.model.PPUserLifestyleDAO;

@Repository
public interface PPUserLifestyleESRepository extends ElasticsearchRepository<PPUserLifestyleDAO, String> {
	
	List<PPUserLifestyleDAO> findByUserid(String userid);

}
