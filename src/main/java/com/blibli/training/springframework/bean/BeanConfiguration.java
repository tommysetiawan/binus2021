package com.blibli.training.springframework.bean;

import com.blibli.training.springframework.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
@Configuration
public class BeanConfiguration {


  @Bean(name = "users")
  public List<User> users(){
    List<User> users = new ArrayList<>();
    User firstUser = User.builder()
        .firstName("tommy")
        .lastName("setiawan")
        .age(18).build();

    User secondUser = User.builder()
        .firstName("jodi")
        .lastName("subarja")
        .age(18).build();
    users.add(firstUser);
    users.add(secondUser);
    return users;
  }
}
