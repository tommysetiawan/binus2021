package com.blibli.training.springframework.service.impl;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.repository.CartRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.AbstractList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author Sebastian Bagya G. on 3/9/2021
 */
public class CartServiceImplTest {

  private static final String CUSTOMER_NAME = "cust";
  private static final String OTHER_CUSTOMER_NAME = "otherCust";
  private static final String ITEM_NAME = "item";
  private static final String OTHER_ITEM_NAME = "otherItem";

  @InjectMocks
  private CartServiceImpl cartService;

  @Mock
  private CartRepository cartRepository;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    List<Cart> expected = Arrays.asList(
        Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build(),
        Cart.builder().customerName(CUSTOMER_NAME).itemName(OTHER_ITEM_NAME).quantity(1).build(),
        Cart.builder().customerName(OTHER_CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build());
    Mockito.when(cartRepository.getCarts()).thenReturn(expected);
  }

  @After
  public void tearDown() throws Exception {
    Mockito.verifyNoMoreInteractions(cartRepository);
  }

  @Test
  public void getCartByCustomerName() {
    List<Cart> carts = cartService.getCartByCustomerName(CUSTOMER_NAME);
    Assert.assertEquals(2, carts.size());
    Mockito.verify(cartRepository).getCarts();
  }

  @Test
  public void addToCart() {
    Cart expected = Cart.builder().customerName(OTHER_CUSTOMER_NAME).itemName(OTHER_ITEM_NAME).quantity(5).build();
    Mockito.when(cartRepository.addCart(expected)).thenReturn(true);
    Cart cart = cartService.addToCart(expected);
    Assert.assertEquals(expected, cart);
    Mockito.verify(cartRepository).addCart(expected);
  }

  @Test
  public void updateCart() {
    Cart expected = Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(10).build();
    Cart cart = cartService.updateCart(expected);
    Assert.assertEquals(expected, cart);
    Mockito.verify(cartRepository).getCarts();
  }

  @Test
  public void updateCart_notFound() {
    Cart expected = Cart.builder().customerName(OTHER_CUSTOMER_NAME).itemName(OTHER_ITEM_NAME).quantity(10).build();
    Cart cart = cartService.updateCart(expected);
    Assert.assertNull(cart);
    Mockito.verify(cartRepository).getCarts();
  }

  @Test
  public void deleteCart() {
    Mockito.when(cartRepository.removeCarts(Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build())).thenReturn(true);
    Assert.assertTrue(cartService.deleteCart(ITEM_NAME, CUSTOMER_NAME));
    Mockito.verify(cartRepository).removeCarts(Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build());
    Mockito.verify(cartRepository).getCarts();
  }

  @Test
  public void deleteCart_notFound() {
    Mockito.when(cartRepository.removeCarts(null)).thenReturn(true);
    Assert.assertTrue(cartService.deleteCart(OTHER_ITEM_NAME, OTHER_CUSTOMER_NAME));
    Mockito.verify(cartRepository).removeCarts(null);
    Mockito.verify(cartRepository).getCarts();
  }
}