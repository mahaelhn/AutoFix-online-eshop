package me.uir.onlineshopping.dto;

import lombok.Data;
import me.uir.onlineshopping.entity.ProductInfo;


@Data
public class Item {
    private ProductInfo productInfo;

    private Integer quantity;

    public Item(ProductInfo productInfo, Integer quantity) {
        this.productInfo = productInfo;
        this.quantity = quantity;
    }
}
