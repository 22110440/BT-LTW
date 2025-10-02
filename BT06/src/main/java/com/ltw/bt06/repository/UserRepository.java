package com.ltw.bt06.repository;

import com.ltw.bt06.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByUsernameContainingIgnoreCase(String username);
}
