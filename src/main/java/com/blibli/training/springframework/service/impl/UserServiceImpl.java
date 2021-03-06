package com.blibli.training.springframework.service.impl;

import com.blibli.training.springframework.entity.User;
import com.blibli.training.springframework.repository.UserRepository;
import com.blibli.training.springframework.repository.impl.UserRepositoryImpl;
import com.blibli.training.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
@Component
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  public List<User> getAll(){
    return userRepository.getUsers();
  }

  public User getUserByName(String name){
    for(User user:userRepository.getUsers()){
      if(user.getFirstName().equals(name)){
        return user;
      }
    }
    return null;
  }

  public User addUser(User user){
    userRepository.getUsers().add(user);
    return user;
  }
}
