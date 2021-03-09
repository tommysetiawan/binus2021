package com.blibli.training.springframework.repository.impl;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
@Component
public class CartRepositoryImpl implements CartRepository {
  @Autowired
  private List<Cart> carts;

  @Override
  public List<Cart> getCarts() {
    return carts;
  }
}
