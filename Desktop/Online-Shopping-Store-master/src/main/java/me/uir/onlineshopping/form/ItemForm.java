package me.uir.onlineshopping.form;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Data
public class ItemForm {
    @Min(value = 1)
    private Integer quantity;
    @NotEmpty
    private String productId;
}
