package com.blibli.training.springframework.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Cart {
  private String itemName;
  private int quantity;
  private String customerName;
}
