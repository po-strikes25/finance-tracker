package com.crm.FinanceTracker.dto;

import com.crm.FinanceTracker.enums.ExpenseType;
import com.crm.FinanceTracker.enums.IncomeType;
import lombok.Data;

import java.time.LocalDate;

@Data
public class IncomeDTO
{
    private Long        id;
    private String      title;
    private String      description;
    private IncomeType  incomeType;
    private LocalDate   date;
    private Double      amount;
}
