package com.crm.FinanceTracker.entity;

import com.crm.FinanceTracker.enums.ExpenseType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        expenseId;
    private String      expenseTitle;
    private String      description;
    private ExpenseType expenseType;
    private LocalDate   date;
    private Double      amount;
}
