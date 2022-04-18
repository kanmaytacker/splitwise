package com.scaler.splitwise.services.settlement;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Transaction;

import java.util.List;

public interface SettlementStrategy {
    List<Transaction> settleExpenses(List<Expense> expenses);
}
