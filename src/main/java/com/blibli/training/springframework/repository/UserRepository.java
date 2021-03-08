package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRepository {
  @Autowired
  @Qualifier("users")
  private List<User> users;

  public List<User> getUsers() {
    return users;
  }

}
