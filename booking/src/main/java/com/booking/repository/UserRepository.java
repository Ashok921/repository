package com.booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.booking.dao.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmailId(String EmailId);
}
