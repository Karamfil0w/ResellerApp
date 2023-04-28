package com.resellerapp.model.entity;

import com.resellerapp.model.entity.enums.ConditionType;

import javax.persistence.*;

@Entity
@Table(name = "conditions")
public class Condition {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.STRING)
    private ConditionType conditionName;

    @Column(columnDefinition = "TEXT")
    private String description;


    //o	For EXCELLENT - "In perfect condition"
    //o	For GOOD – "Some signs of wear and tear or minor defects"
    //o	For ACCEPTABLE – "The item is fairly worn but continues to function properly"

    public Condition() {
    }

    public Condition(ConditionType conditionName) {
        this.conditionName = conditionName;
        switch (conditionName) {
            case EXCELLENT:
                this.description = "In perfect condition";
                break;
            case GOOD:
                this.description = "Some signs of wear and tear or minor defects";
                break;
            case ACCEPTABLE:
                this.description = "The item is fairly worn but continues to function properly";
                break;
            default:
                throw new IllegalArgumentException("Invalid ConditionType: " + conditionName);
        }

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ConditionType getConditionName() {
        return conditionName;
    }

    public void setConditionName(ConditionType conditionName) {
        this.conditionName = conditionName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
