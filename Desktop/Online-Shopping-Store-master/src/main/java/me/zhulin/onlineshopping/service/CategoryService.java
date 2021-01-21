package me.zhulin.onlineshopping.service;

import me.zhulin.onlineshopping.entity.ProductCategory;
import me.zhulin.onlineshopping.entity.ProductInfo;

import javax.persistence.OrderBy;
import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface CategoryService {

    List<ProductCategory> findAll();

    ProductCategory findByCategoryType(Integer categoryType);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
