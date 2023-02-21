package com.projecthub.service;

import com.projecthub.dao.UserRepository;
import com.projecthub.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers(){
        return userRepository.selectAllUsers();
    }
    public User getUserById(Long id){
        return userRepository.selectUserById(id).orElseThrow();
    }
    public User getUserByEmail(String email){
        return userRepository.selectUserByEmail(email).orElseThrow();
    }

    public void addUser(User user){
        if(userRepository.existsUserWithEmail(user.getEmail())){
            throw new RuntimeException("email already taken");
        }

        userRepository.insertUser(user);
    }

    public void deleteUserById(Long userId){
        if(!userRepository.existsUserWithId(userId)){
            throw new RuntimeException("user with id [%s] not found".formatted(userId));
        }
        userRepository.deleteUserWithId(userId);
    }
    public void updateUser(Long userId,User user){
        User userToUpdate = userRepository.selectUserById(userId).orElseThrow();


        if(user.getUserName() != null && !user.getUserName().equals(userToUpdate.getUserName())){
            userToUpdate.setUserName(user.getUserName());
        }
        if(user.getEmail() != null && !user.getEmail().equals(userToUpdate.getEmail())){
            if(userRepository.existsUserWithEmail(user.getEmail())){
                throw new RuntimeException("Email is already taken");
            }
            userToUpdate.setEmail(user.getEmail());
        }
        if(user.getDescription() != null && !user.getDescription().equals(userToUpdate.getDescription())){
            userToUpdate.setDescription(user.getDescription());
        }
        if(user.getAvatarUrl() != null && !user.getAvatarUrl().equals(userToUpdate.getAvatarUrl())){
            userToUpdate.setAvatarUrl(user.getAvatarUrl());
        }
        if(user.getHonor() !=  userToUpdate.getHonor()){
            userToUpdate.setHonor(user.getHonor());
        }

        userRepository.updateUser(userToUpdate);
    }
}
