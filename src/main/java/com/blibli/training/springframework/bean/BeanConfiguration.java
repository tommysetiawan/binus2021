package com.blibli.training.springframework.bean;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.entity.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
@Configuration
public class BeanConfiguration {


  @Bean(name = "users")
  public List<User> users(){
    User firstUser = User.builder().firstName("tommy").lastName("setiawan").age(18).build();
    User secondUser = User.builder().firstName("jodi").lastName("subarja").age(18).build();
    User thirdUser = User.builder().firstName("sebastian").lastName("bagya").age(18).build();
    return new ArrayList<>(Arrays.asList(firstUser, secondUser, thirdUser));
  }

  @Bean(name = "carts")
  public List<Cart> carts(){
    return new ArrayList<>();
  }
}
