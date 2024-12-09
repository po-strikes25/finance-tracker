package com.crm.FinanceTracker.dto;

import com.crm.FinanceTracker.entity.Expense;
import com.crm.FinanceTracker.entity.Income;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class GraphDTO
{
    List<Income>    incomeList;
    List<Expense>   expenseList;
}
