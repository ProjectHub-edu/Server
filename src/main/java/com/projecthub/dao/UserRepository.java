package com.projecthub.dao;

import com.projecthub.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    List<User> selectAllUsers();
    Optional<User> selectUserById(Long id);
    Optional<User> selectUserByEmail(String email);
    void insertUser(User user);
    boolean existsUserWithEmail(String email);

    boolean existsUserWithId(Long id);
    void deleteUserWithId(Long id);
    void updateUser(User user);
}
