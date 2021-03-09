package com.blibli.training.springframework.service;

import com.blibli.training.springframework.entity.User;

import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
public interface UserService {
  List<User> getAll();

  User getUserByName(String name);

  User addUser(User user);
}
