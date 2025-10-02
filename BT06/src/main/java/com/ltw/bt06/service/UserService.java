package com.ltw.bt06.service;

import com.ltw.bt06.model.User;
import com.ltw.bt06.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository repo;

    public UserService(UserRepository repo) {
        this.repo = repo;
    }

    public List<User> getAll() {
        return repo.findAll();
    }

    public User getById(Long id) {
        return repo.findById(id).orElse(null);
    }

    public User save(User user) {
        return repo.save(user);
    }

    public void delete(Long id) {
        repo.deleteById(id);
    }

    public List<User> search(String keyword) {
        return repo.findByUsernameContainingIgnoreCase(keyword);
    }
}
