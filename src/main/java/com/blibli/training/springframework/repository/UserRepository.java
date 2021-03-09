package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.User;

import java.util.List;

public interface UserRepository {
  List<User> getUsers();
}
