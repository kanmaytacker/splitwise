package com.scaler.splitwise.services.settlement;

import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.Transaction;
import com.scaler.splitwise.models.User;
import org.springframework.data.util.Pair;

import java.util.*;

public class GreedySettlementStrategy implements SettlementStrategy {

    private static Map<Long, Long> prepareInitialState(List<Expense> expenses) {
        Map<Long, Long> transactions = new HashMap<>();
        for (Expense expense : expenses) {
            for (User user : expense.getOwedBy().keySet()) {
                if (!transactions.containsKey(user.getId())) {
                    transactions.put(user.getId(), 0L);
                }

                transactions.put(user.getId(), transactions.get(user.getId()) + expense.getOwedBy().get(user));
            }

            for (User user : expense.getPaidBy().keySet()) {
                if (!transactions.containsKey(user.getId())) {
                    transactions.put(user.getId(), 0L);
                }

                transactions.put(user.getId(), transactions.get(user.getId()) - expense.getPaidBy().get(user));
            }
        }
        return transactions;
    }

    @Override
    public List<Transaction> settleExpenses(List<Expense> expenses) {
        Map<Long, Long> initialState = prepareInitialState(expenses);
        TreeSet<Pair<Long, Long>> expenseTree = new TreeSet<>((left, right) -> (int) (left.getFirst() - right.getFirst()));
        for (Map.Entry<Long, Long> entry : initialState.entrySet()) {
            expenseTree.add(Pair.of(entry.getValue(), entry.getKey()));
        }

        List<Transaction> transactions = new ArrayList<>();

        while (expenseTree.size() > 1) {
            Pair<Long, Long> smallestPair = expenseTree.first();
            Pair<Long, Long> largestPair = expenseTree.last();

            Transaction transaction = new Transaction();
            transaction.setFromId(largestPair.getSecond());
            transaction.setToId(smallestPair.getSecond());
            transaction.setAmount(largestPair.getFirst());

            expenseTree.remove(largestPair);
            expenseTree.remove(smallestPair);

            expenseTree.add(Pair.of(smallestPair.getFirst() + largestPair.getFirst(), smallestPair.getSecond()));
            transactions.add(transaction);
        }

        return transactions;
    }

}
