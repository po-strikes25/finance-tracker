package com.crm.FinanceTracker.service.income;

import com.crm.FinanceTracker.dto.ExpenseDTO;
import com.crm.FinanceTracker.entity.Expense;
import com.crm.FinanceTracker.repo.ExpenseRepository;
import com.crm.FinanceTracker.service.income.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements ExpenseService
{
    private final ExpenseRepository expenseRepository;

    @Override
    public Expense postExpense(ExpenseDTO expenseDTO)
    {
        return saveOrUpdate(new Expense(), expenseDTO);
    }

    private Expense saveOrUpdate(Expense expense, ExpenseDTO expenseDTO)
    {
        /*
        Expense expense = Expense.builder()
                .expenseTitle(expenseDTO.getTitle())
                .description(expenseDTO.getDescription())
                .expenseType(expenseDTO.getExpenseType())
                .date(expenseDTO.getDate())
                .amount(expenseDTO.getAmount())
                .build();
        */

        expense.setExpenseTitle(expenseDTO.getTitle());
        expense.setDescription(expenseDTO.getDescription());
        expense.setExpenseType(expenseDTO.getExpenseType());
        expense.setDate(expenseDTO.getDate());
        expense.setAmount(expenseDTO.getAmount());

        return expenseRepository.save(expense);
    }

    @Override
    public List<Expense> getAllExpenses()
    {
        return findAllExpenses();
    }

    private List<Expense> findAllExpenses()
    {
        return expenseRepository.findAll().stream()
                //.sorted((o1, o2) -> { return o2.getDate().compareTo(o1.getDate()); })
                .sorted(Comparator.comparing(Expense::getDate).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Expense getExpenseByID(Long expenseId)
    {
        return fetchExpenseByID(expenseId);
    }

    private Expense fetchExpenseByID(Long expenseId)
    {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);

        return expenseOptional.orElseThrow(() -> new EntityNotFoundException("Expense with ID: " + expenseId + " not found !"));
    }

    @Override
    public Expense updateExpenseByID(Long expenseId, ExpenseDTO expenseDTO)
    {
        return putExpenseByID(expenseId, expenseDTO);
    }

    private Expense putExpenseByID(Long expenseId, ExpenseDTO expenseDTO)
    {
        Optional<Expense> expenseOptional = expenseRepository.findById(expenseId);

        if(expenseOptional.isPresent())
        {
            return saveOrUpdate(expenseOptional.get(), expenseDTO);
        }
        else
        {
            throw new EntityNotFoundException("Expense with ID: " + expenseId + " not found !");
        }
    }

    @Override
    public void deleteExpense(Long id)
    {
        deleteExpenseByID(id);
    }

    private void deleteExpenseByID(Long id)
    {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);

        if(optionalExpense.isPresent())
        {
            expenseRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Expense with ID: " + id + " not found !");
        }
    }

    @Override
    public void deleteExpenses()
    {
        deleteAllExpenses();
    }

    private void deleteAllExpenses()
    {
        expenseRepository.deleteAll();
    }
}
