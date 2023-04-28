package com.resellerapp.model.entity;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "offers")
public class Offer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 50)
    private String description;

    @Column(nullable = false)
    private BigDecimal price;

    @ManyToOne(optional = false)
    @JoinColumn(name = "condition_id")
    private Condition condition;

    @ManyToOne(fetch = FetchType.EAGER )
//            (cascade = CascadeType.ALL)
//    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;


    //•	Has a Condition (not null)
    //o	One offer has one contition and one condition can have many offers.


    public Offer() {
    }

    public Offer(Offer offer) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "description='" + description + '\'' +
                ", price=" + price +
                ", condition=" + condition +
                '}';
    }
}
