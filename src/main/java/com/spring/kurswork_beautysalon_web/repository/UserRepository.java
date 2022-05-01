package com.spring.kurswork_beautysalon_web.repository;

import com.spring.kurswork_beautysalon_web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByLogin(String login);

    @Override
    Optional<User> findById(Long id);
}