package com.blibli.training.springframework.controller;

import com.blibli.training.springframework.entity.Cart;
import com.blibli.training.springframework.service.CartService;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

/**
 * @author Sebastian Bagya G. on 3/9/2021
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CartControllerTest {

  private static final String CUSTOMER_NAME = "cust";
  private static final String ITEM_NAME = "item";
  private static final String OTHER_ITEM_NAME = "otherItem";

  ObjectMapper objectMapper = new ObjectMapper();

  @MockBean
  private CartService cartService;

  @LocalServerPort
  private int serverPort;

  @BeforeEach
  void setUp() {
    RestAssured.port = serverPort;
  }

  @AfterEach
  void tearDown() {
    Mockito.verifyNoMoreInteractions(cartService);
  }

  @Test
  void getCartByCustomer() {
    List<Cart> expected = Arrays.asList(
        Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build(),
        Cart.builder().customerName(CUSTOMER_NAME).itemName(OTHER_ITEM_NAME).quantity(1).build());

    Mockito.when(cartService.getCartByCustomerName(CUSTOMER_NAME)).thenReturn(expected);

    List<Cart> result =
        RestAssured.given()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .header("Accept", MediaType.APPLICATION_JSON_VALUE)
            .param("customerName", CUSTOMER_NAME)
            .when()
            .get("/carts")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract().body().jsonPath().getList(".", Cart.class);

    Assert.assertEquals(expected.size(), result.size());
    Assert.assertEquals(expected.get(0).getCustomerName(), result.get(0).getCustomerName());

    Mockito.verify(cartService).getCartByCustomerName(CUSTOMER_NAME);
  }

  @Test
  void addToCart() throws Exception {
    Cart expected = Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build();

    Mockito.when(cartService.addToCart(expected)).thenReturn(expected);

    Cart result =
        RestAssured.given()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .header("Accept", MediaType.APPLICATION_JSON_VALUE)
            .body(objectMapper.writeValueAsString(expected))
            .when()
            .post("/carts")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract().body().jsonPath().getObject(".", Cart.class);

    Assert.assertEquals(expected.getCustomerName(), result.getCustomerName());
    Assert.assertEquals(expected.getQuantity(), result.getQuantity());

    Mockito.verify(cartService).addToCart(expected);
  }

  @Test
  void updateCart() throws Exception {
    Cart expected = Cart.builder().customerName(CUSTOMER_NAME).itemName(ITEM_NAME).quantity(1).build();

    Mockito.when(cartService.updateCart(expected)).thenReturn(expected);

    Cart result =
        RestAssured.given()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .header("Accept", MediaType.APPLICATION_JSON_VALUE)
            .body(objectMapper.writeValueAsString(expected))
            .when()
            .put("/carts")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract().body().jsonPath().getObject(".", Cart.class);

    Assert.assertEquals(expected.getCustomerName(), result.getCustomerName());
    Assert.assertEquals(expected.getQuantity(), result.getQuantity());

    Mockito.verify(cartService).updateCart(expected);
  }

  @Test
  void delete() {
    Mockito.when(cartService.deleteCart(ITEM_NAME, CUSTOMER_NAME)).thenReturn(true);

    Boolean result =
        RestAssured.given()
            .header("Content-Type", MediaType.APPLICATION_JSON_VALUE)
            .header("Accept", MediaType.APPLICATION_JSON_VALUE)
            .param("itemName", ITEM_NAME)
            .param("customerName", CUSTOMER_NAME)
            .when()
            .delete("/carts")
            .then()
            .statusCode(HttpStatus.OK.value())
            .extract().body().as(Boolean.class);

    Assert.assertEquals(true, result);

    Mockito.verify(cartService).deleteCart(ITEM_NAME, CUSTOMER_NAME);
  }
}