package com.crm.FinanceTracker.repo;

import com.crm.FinanceTracker.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long>
{
    /*  Implementation automatically provided by Spring Data JPA
        findByDateBetween() : "findBy" -> QUERY | "Date" -> FIELD | "Between" -> CONDITION
    */
    List<Expense> findByDateBetween(LocalDate startDate, LocalDate endDate);

    /*  FOR MORE CONTROL OVER THE QUERY:
        @Query("SELECT e FROM Expense e WHERE e.date BETWEEN :startDate AND :endDate")
        List<Expense> findExpensesBetweenDates( @Param("startDate") LocalDate startDate,
                                                @Param("endDate") LocalDate endDate);
    */

    /*  JPQL QUERY CUSTOMISED
        @Query("SELECT e FROM Expense e WHERE e.date >= :startDate")
        List<Expense> findByDateBetween(@Param("startDate") LocalDate startDate);
    */

    @Query("SELECT SUM(e.amount) FROM Expense e")
    Double sumAllAmounts();

    Optional<Expense> findFirstByOrderByDateDesc();
}
