package com.crm.FinanceTracker.service.expense;

import com.crm.FinanceTracker.dto.IncomeDTO;
import com.crm.FinanceTracker.entity.Income;

import java.util.List;

public interface IncomeService
{
    Income postIncome(IncomeDTO incomeDTO);

    List<IncomeDTO> getAllIncomes();

    IncomeDTO getIncomeByID(Long incomeId);

    Income updateIncomeByID(Long incomeId, IncomeDTO incomeDTO);

    void deleteIncome(Long id);

    void deleteIncomes();
}
