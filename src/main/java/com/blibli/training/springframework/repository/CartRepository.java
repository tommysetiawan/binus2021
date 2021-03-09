package com.blibli.training.springframework.repository;

import com.blibli.training.springframework.entity.Cart;

import java.util.List;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
public interface CartRepository {
  List<Cart> getCarts();
  boolean removeCarts(Cart cart);
  boolean addCart(Cart cart);
}
