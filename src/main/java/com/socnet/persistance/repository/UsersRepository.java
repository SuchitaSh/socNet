package com.socnet.persistance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socnet.persistance.entities.User;

public interface UsersRepository extends JpaRepository<User, Long>{

}
