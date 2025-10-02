package com.ltw.graphqlshop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.ltw.graphqlshop.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {}
