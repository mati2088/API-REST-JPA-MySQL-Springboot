package com.microservice.tutorial.app.repository;


import com.microservice.tutorial.app.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface UserRepository  extends JpaRepository<User, Long> {




}
