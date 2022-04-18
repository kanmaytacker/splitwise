package com.scaler.splitwise.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Transaction {
    private Long fromId;
    private Long toId;
    private Long amount;
}
