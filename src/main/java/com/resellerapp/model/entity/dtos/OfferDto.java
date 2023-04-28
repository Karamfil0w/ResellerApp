package com.resellerapp.model.entity.dtos;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

public class OfferDto {

    private Long userId;

    private Long offerId;
    @NotBlank
    private String condition;

    @Positive
    @NotBlank
    private BigDecimal price;

    @Size(min = 2, max = 50)
    private String description;

    public OfferDto() {
    }

    public OfferDto(long userId, Long offerId, String condition, BigDecimal price, String description) {
        this.userId = userId;
        this.offerId = offerId;
        this.condition = condition;
        this.price = price;
        this.description = description;
    }

    public OfferDto(OfferDto offerDto) {

    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getOfferId() {
        return offerId;
    }

    public void setOfferId(Long offerId) {
        this.offerId = offerId;
    }
}
