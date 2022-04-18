package com.scaler.splitwise.dtos;

import com.scaler.splitwise.models.Transaction;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
public class SettleResponseDTO {
    List<Transaction> transactions = new ArrayList<>();
}
