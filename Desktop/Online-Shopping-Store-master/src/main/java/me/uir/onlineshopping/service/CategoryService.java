package me.uir.onlineshopping.service;

import me.uir.onlineshopping.entity.ProductCategory;

import java.util.List;


public interface CategoryService {

    List<ProductCategory> findAll();

    ProductCategory findByCategoryType(Integer categoryType);

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
