package com.crm.FinanceTracker.repo;

import com.crm.FinanceTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface IncomeRepository extends JpaRepository<Income, Long>
{
    List<Income> findIncomeInDateRange(LocalDate startDate, LocalDate endDate);
}
