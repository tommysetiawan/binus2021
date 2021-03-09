package com.blibli.training.springframework.service;


import com.blibli.training.springframework.entity.Cart;

import java.util.List;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
public interface CartService {
  List<Cart> getCartByCustomerName(String customerName);
  Cart addToCart(Cart cart);
  Cart updateCart(Cart cart);
  boolean deleteCart(String itemName, String customerName);
}
