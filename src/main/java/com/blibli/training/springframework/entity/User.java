package com.blibli.training.springframework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tommy.setiawan on 3/8/2021.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
  private String firstName;
  private String lastName;
  private int age;
}
