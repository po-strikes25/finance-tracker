package com.crm.FinanceTracker.repo;

import com.crm.FinanceTracker.entity.Expense;
import com.crm.FinanceTracker.entity.Income;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IncomeRepository extends JpaRepository<Income, Long>
{
    /*  Implementation automatically provided by Spring Data JPA
        findByDateBetween() : "findBy" -> QUERY | "Date" -> FIELD | "Between" -> CONDITION
    */
    List<Income> findByDateBetween(LocalDate startDate, LocalDate endDate);

    @Query("SELECT SUM(i.amount) FROM Income i")
    Double sumAllAmounts();

    Optional<Income> findFirstByOrderByDateDesc();
}
