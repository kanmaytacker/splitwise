package com.scaler.splitwise.models;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.Map;

@Data
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "EXPENSES")
public class Expense extends BaseModel {
    @ManyToOne
    private User createdBy;

    private long amount;

    private Currency currency;

    @ElementCollection
    Map<User, Long> paidBy = new HashMap<>();

    @ElementCollection
    Map<User, Long> owedBy = new HashMap<>();
}
