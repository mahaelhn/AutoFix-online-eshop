package me.zhulin.onlineshopping.dto;

import lombok.Data;
import me.zhulin.onlineshopping.entity.ProductInfo;


@Data
public class Item {
    private ProductInfo productInfo;

    private Integer quantity;

    public Item(ProductInfo productInfo, Integer quantity) {
        this.productInfo = productInfo;
        this.quantity = quantity;
    }
}
