package me.uir.onlineshopping.service.impl;

import me.uir.onlineshopping.dto.Item;
import me.uir.onlineshopping.entity.OrderMain;
import me.uir.onlineshopping.entity.ProductInOrder;
import me.uir.onlineshopping.entity.ProductInfo;
import me.uir.onlineshopping.entity.User;
import me.uir.onlineshopping.enums.ProductStatusEnum;
import me.uir.onlineshopping.enums.ResultEnum;
import me.uir.onlineshopping.exception.MyException;
import me.uir.onlineshopping.form.ItemForm;
import me.uir.onlineshopping.repository.OrderRepository;
import me.uir.onlineshopping.service.CartService;
import me.uir.onlineshopping.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.*;


@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartServiceImpl implements CartService {
    @Autowired
    ProductService productService;
    @Autowired
    OrderRepository orderRepository;

    private Map<String, Item> map = new LinkedHashMap<>();

    @Override
    public void addItem(ItemForm itemForm) {
        ProductInfo productInfo = productService.findOne(itemForm.getProductId());

        if (productInfo.getProductStatus() == ProductStatusEnum.DOWN.getCode()) {
            throw new MyException(ResultEnum.PRODUCT_OFF_SALE);
        }

        // Check whether is in the cart
        if(map.containsKey(itemForm.getProductId())){
            // Update quantity
            Integer old = map.get(itemForm.getProductId()).getQuantity();
            itemForm.setQuantity(old + itemForm.getQuantity());
        }

        map.put(itemForm.getProductId(), new Item(productInfo, itemForm.getQuantity()));
    }

    @Override
    public void removeItem(String productId) {
        if (!map.containsKey(productId)) throw new MyException(ResultEnum.PRODUCT_NOT_IN_CART);
        map.remove(productId);
    }

    @Override
    public void updateQuantity(String productId, Integer quantity) {
        if (!map.containsKey(productId)) throw new MyException(ResultEnum.PRODUCT_NOT_IN_CART);
        Item item = map.get(productId);
        Integer max = item.getProductInfo().getProductStock();
        if(quantity > 0) {
            item.setQuantity(quantity > max ? max : quantity);
        }
    }

    @Override
    public Collection<Item> findAll() {
        return map.values();
    }

    @Override
    @Transactional
    public void checkout(User user) {
        OrderMain orderMain = new OrderMain(user);
        for (String productId : map.keySet()) {
            Item item = map.get(productId);
            ProductInOrder productInOrder = new ProductInOrder(item.getProductInfo(), item.getQuantity());
            productInOrder.setOrderMain(orderMain);
            orderMain.getProducts().add(productInOrder);
            productService.decreaseStock(productId, item.getQuantity());
        }
        orderMain.setOrderAmount(getTotal());
        orderRepository.save(orderMain);
        map.clear();
    }

    @Override
    public BigDecimal getTotal() {
        Collection<Item> items = findAll();
        BigDecimal total = new BigDecimal(0);
        for (Item item : items) {
            BigDecimal price = item.getProductInfo().getProductPrice();
            BigDecimal quantity = new BigDecimal(item.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total;
    }
}
