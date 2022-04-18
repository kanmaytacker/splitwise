package com.scaler.splitwise.controllers;

import com.scaler.splitwise.dtos.ResponseDTO;
import com.scaler.splitwise.models.CreateExpenseDTO;
import com.scaler.splitwise.models.Expense;
import com.scaler.splitwise.models.ExpenseDTO;
import com.scaler.splitwise.services.ExpenseService;
import com.scaler.splitwise.utils.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Controller
public class ExpenseController {
    @Autowired
    private ExpenseService expenseService;

    @PostMapping("/expense")
    public ResponseDTO<ExpenseDTO> createExpense(@RequestBody CreateExpenseDTO expenseRequest) {
        Optional<ExpenseDTO> expense = expenseService.createUserExpense(expenseRequest.getUserId(), expenseRequest);
        return ResponseUtils.toResponse(expense);
    }

    @GetMapping("/expense/{expenseId}")
    public ResponseDTO<ExpenseDTO> getExpense(@PathVariable Long expenseId) {
        Optional<Expense> expense = expenseService.getExpense(expenseId);
        return ResponseUtils.toResponse(expense.map(ExpenseDTO::from));
    }
}
