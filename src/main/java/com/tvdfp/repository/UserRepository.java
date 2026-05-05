package com.tvdfp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.tvdfp.model.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Long> {

    Users findByUsername(String username);

    Users findByEmail(String email);

    Users findByUsernameAndPassword(String username, String password);
    
    Users findByEmailAndPassword (String email, String password);
}
