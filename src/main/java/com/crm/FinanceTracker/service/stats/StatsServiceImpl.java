package com.crm.FinanceTracker.service.stats;

import com.crm.FinanceTracker.dto.GraphDTO;
import com.crm.FinanceTracker.dto.StatsDTO;
import com.crm.FinanceTracker.entity.Expense;
import com.crm.FinanceTracker.entity.Income;
import com.crm.FinanceTracker.repo.ExpenseRepository;
import com.crm.FinanceTracker.repo.IncomeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.OptionalDouble;

@Service
@RequiredArgsConstructor
@Slf4j
public class StatsServiceImpl implements StatsService
{
    /*  IF SLF4J NOT IMPORTED THEN MANUALLY GET LOGGER
        private static final Logger log = LoggerFactory.getLogger(StatsServiceImpl.class);
    */

    private final IncomeRepository  incomeRepository;
    private final ExpenseRepository expenseRepository;

    @Override
    public GraphDTO getChartData()
    {
        LocalDate   endDate     = LocalDate.now();
        LocalDate   startDate   = endDate.minusDays(27);
        GraphDTO    graphDTO    = new GraphDTO();

        /*  log.info:   Used for informational messages like the date range being fetched.
            log.debug:  Used for debugging information, like the sizes of the fetched lists.
            log.error:  Used for logging errors or exceptions.
        */
        log.info("Fetching chart data between dates: {} and {}", startDate, endDate);

        graphDTO.setExpenseList(expenseRepository.findByDateBetween(startDate, endDate));
        graphDTO.setIncomeList(incomeRepository.findByDateBetween(startDate, endDate));

        return graphDTO;
    }

    @Override
    public StatsDTO getStats()
    {
        Double              totalIncome     =   incomeRepository.sumAllAmounts();
        Double              totalExpense    =   expenseRepository.sumAllAmounts();
        Optional<Income>    optionalIncome  =   incomeRepository.findFirstByOrderByDateDesc();
        Optional<Expense>   optionalExpense =   expenseRepository.findFirstByOrderByDateDesc();
        StatsDTO            statsDTO        =   new StatsDTO();

        statsDTO.setIncome(totalIncome);
        statsDTO.setExpense(totalExpense);

        optionalIncome.ifPresent(statsDTO::setLatestIncome);
        optionalExpense.ifPresent(statsDTO::setLatestExpense);

        statsDTO.setBalance(totalIncome - totalExpense);

        List<Income>    incomeList  =   incomeRepository.findAll();
        List<Expense>   expenseList =   expenseRepository.findAll();
        OptionalDouble  minIncome   =   incomeList.stream().mapToDouble(Income::getAmount).min();
        OptionalDouble  maxIncome   =   incomeList.stream().mapToDouble(Income::getAmount).max();
        OptionalDouble  minExpense  =   expenseList.stream().mapToDouble(Expense::getAmount).min();
        OptionalDouble  maxExpense  =   expenseList.stream().mapToDouble(Expense::getAmount).max();

        minIncome.ifPresent(statsDTO::setMinIncome);
        maxIncome.ifPresent(statsDTO::setMaxIncome);
        minExpense.ifPresent(statsDTO::setMinExpense);
        maxExpense.ifPresent(statsDTO::setMaxExpense);

        return statsDTO;
    }
}
