package com.projecthub.dao;

import com.projecthub.entity.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("UserList")
public class UserListRepository implements UserRepository {

    private static final List<User> users;

    static {
        users = new ArrayList<>();

        users.add(new User(1L, "Mark", "mark@gmail.com", "pas1"));
        users.add(new User(2L, "Bohdan", "bohdan@gmail.com", "pas2"));
        users.add(new User(3L, "Maks", "maks@gmail.com", "pas3"));
    }

    @Override
    public List<User> selectAllUsers() {
        return users;
    }

    @Override
    public Optional<User> selectUserById(Long id) {
        return users.stream().filter(user -> Objects.equals(user.getId(), id)).findAny();
    }

    @Override
    public Optional<User> selectUserByEmail(String email) {
        return users.stream().filter(user -> user.getEmail().equals(email)).findAny();
    }

    @Override
    public void insertUser(User user) {
        users.add(user);
    }

    @Override
    public boolean existsUserWithEmail(String email) {
        return users.stream().anyMatch(user -> user.getEmail().equals(email));
    }

    @Override
    public boolean existsUserWithId(Long id) {
        return users.stream().anyMatch(user -> Objects.equals(user.getId(), id));
    }

    @Override
    public void deleteUserWithId(Long id) {
        users.remove(selectUserById(id).orElseThrow());
    }

    @Override
    public void updateUser(User user) {
        users.set(users.indexOf(selectUserById(user.getId()).orElseThrow()),user);
    }
}
