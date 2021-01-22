package me.uir.onlineshopping.repository;

import me.uir.onlineshopping.entity.ProductInOrder;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductInOrderRepository extends JpaRepository<ProductInOrder, Long> {

}
