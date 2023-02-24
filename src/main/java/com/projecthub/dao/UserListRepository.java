package com.projecthub.dao;

import com.projecthub.entity.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("UserList")
public class UserListRepository implements UserRepository {

    static final List<User> users;

    private static Long id;

    static {
        users = new ArrayList<>();
        id = 0L;

        users.add(User.builder().id(id++).username("Mark").email("makr@gmail.com").password(new BCryptPasswordEncoder().encode("pas1")).build());
        users.add(User.builder().id(id++).username("Bohdan").email("bohdan@gmail.com").password(new BCryptPasswordEncoder().encode("pas2")).build());
        users.add(User.builder().id(id++).username("Maks").email("maks@gmail.com").password(new BCryptPasswordEncoder().encode("pas3")).build());
        users.add(User.builder().id(id++).username("Tom").email("tom@gmail.com").password(new BCryptPasswordEncoder().encode("pas4")).build());
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
        user.setId(id++);
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
