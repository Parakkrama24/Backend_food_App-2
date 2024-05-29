package com.Dasanayaka.Backend.repository;

import com.Dasanayaka.Backend.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public User findByEmail(String username);
}

