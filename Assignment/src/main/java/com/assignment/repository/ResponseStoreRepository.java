package com.assignment.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.assignment.dao.Response;

@Repository
public interface ResponseStoreRepository extends JpaRepository<Response, Integer> {

	
}
