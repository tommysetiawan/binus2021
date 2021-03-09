package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by tommy.setiawan on 3/9/2021.
 */
@RestController
@RequestMapping(path = "/carts")
public class CartController {

  @Autowired
  private CartService cartService;

  @GetMapping()
  public List<Cart> getCartByCustomer(@RequestParam String customerName){
    return cartService.getCartByCustomerName(customerName);
  }

  @PostMapping
  public Cart addToCart(@RequestBody Cart cart){
    return cartService.addToCart(cart);
  }

  @PutMapping
  public Cart updateCart(@RequestBody Cart cart){
    return cartService.updateCart(cart);
  }

  @DeleteMapping
  public boolean delete(@RequestParam String itemName, @RequestParam String customerName){
     cartService.deleteCart(itemName, customerName);
     return true;
  }
}
