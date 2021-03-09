package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.entity.User;
import com.blibli.training.springframework.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
@RestController
@RequestMapping(path = "/users")
public class UserController {

  @Autowired
  private UserService userService;

  @RequestMapping(method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  public @ResponseBody List<User> getAll(){
    return userService.getAll();
  }

  @RequestMapping(path = "/{name}" , method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
  public @ResponseBody List<User> getUserByFirstName(@PathVariable String name){
    return userService.getUserByName(name);
  }

  @RequestMapping(method = RequestMethod.POST, produces = {MediaType.APPLICATION_JSON_VALUE})
  public @ResponseBody User addUser(@RequestBody User user){
    return userService.addUser(user);
  }
}
