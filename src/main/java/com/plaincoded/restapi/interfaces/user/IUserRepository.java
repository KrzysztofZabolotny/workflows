package com.plaincoded.restapi.interfaces.user;

import com.plaincoded.restapi.entities.userEntities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {

    List<User> findAll();
    User findByUsername(String username);
    User findByEmail(String email);
}
