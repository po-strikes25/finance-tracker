package com.crm.FinanceTracker.dto;

import com.crm.FinanceTracker.enums.ExpenseType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ExpenseDTO
{
    private Long        id;
    private String      title;
    private String      description;
    private ExpenseType expenseType;
    private LocalDate   date;
    private Double      amount;
}
