package com.blibli.training.springframework.service.impl;

import com.blibli.training.springframework.entity.User;
import com.blibli.training.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
@Component
public class UserServiceImpl implements UserService {
  @Autowired
  @Qualifier("users")
  private List<User> users;

  public List<User> getAll(){
    return users;
  }

  public List<User> getUserByName(String name){
    List<User> userResult = new ArrayList<>();
    for(User user:users){
      if(user.getFirstName().equals(name)){
        userResult.add(user);
      }
    }
    return userResult;
  }

  public User addUser(User user){
    users.add(user);
    return user;
  }
}
