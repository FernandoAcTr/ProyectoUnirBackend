package com.unir.d1001.auth.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.unir.d1001.auth.entities.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
