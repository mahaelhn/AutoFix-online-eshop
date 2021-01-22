package me.uir.onlineshopping.entity;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Data
@DynamicUpdate
public class ProductCategory implements Serializable{

    @Id
    @GeneratedValue
    private Integer categoryId;


    private String categoryName;


    @NaturalId
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
