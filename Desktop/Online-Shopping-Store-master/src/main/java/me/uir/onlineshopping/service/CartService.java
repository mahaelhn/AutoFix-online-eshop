package me.uir.onlineshopping.service;

import me.uir.onlineshopping.dto.Item;
import me.uir.onlineshopping.entity.User;
import me.uir.onlineshopping.form.ItemForm;

import java.math.BigDecimal;
import java.util.Collection;


public interface CartService {
    void addItem(ItemForm itemForm);
    void removeItem(String productId);
    void updateQuantity(String productId, Integer quantity);

    Collection<Item> findAll();

    void  checkout(User user);

    BigDecimal getTotal();

}
