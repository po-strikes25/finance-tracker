package com.crm.FinanceTracker.service.income;

import com.crm.FinanceTracker.dto.ExpenseDTO;
import com.crm.FinanceTracker.entity.Expense;

import java.util.List;

public interface ExpenseService
{
    Expense postExpense(ExpenseDTO expenseDTO);

    List<Expense> getAllExpenses();

    Expense getExpenseByID(Long expenseId);

    Expense updateExpenseByID(Long expenseId, ExpenseDTO expenseDTO);

    void deleteExpense(Long id);

    void deleteExpenses();
}
