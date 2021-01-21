package me.zhulin.onlineshopping.repository;

import me.zhulin.onlineshopping.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;


public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

}
