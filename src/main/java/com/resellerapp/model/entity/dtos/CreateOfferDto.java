package com.resellerapp.model.entity.dtos;

import com.resellerapp.model.entity.Condition;
import com.resellerapp.model.entity.enums.ConditionType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class CreateOfferDto {

    @NotBlank
    @Size(min = 2, max = 50)
    private String description;


    @NotNull
    @Positive
    private BigDecimal price;


    @NotNull
    private ConditionType condition;

    public CreateOfferDto() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public ConditionType getCondition() {
        return condition;
    }

    public void setCondition(ConditionType condition) {
        this.condition = condition;
    }
}
