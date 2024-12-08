package com.crm.FinanceTracker.controller;

import com.crm.FinanceTracker.dto.IncomeDTO;
import com.crm.FinanceTracker.entity.Income;
import com.crm.FinanceTracker.service.expense.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/income")
@RequiredArgsConstructor
@CrossOrigin("*")
public class IncomeController
{
    private final IncomeService incomeService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllIncomes()
    {
        return ResponseEntity.ok(incomeService.getAllIncomes());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getExpenseByID(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(incomeService.getIncomeByID(id));
        }
        catch(EntityNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
        catch(Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PutMapping("/put/{id}")
    public ResponseEntity<?> updateIncomeByID(@PathVariable Long id, @RequestBody IncomeDTO incomeDTO)
    {
        try
        {
            return ResponseEntity.ok(incomeService.updateIncomeByID(id, incomeDTO));
        }
        catch(EntityNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
        catch(Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @PostMapping("/post")
    public ResponseEntity<?> postExpense(@RequestBody IncomeDTO incomeDTO)
    {
        Income createdIncome = incomeService.postIncome(incomeDTO);

        if(createdIncome != null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdIncome);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteIncomeByID(@PathVariable Long id)
    {
        try
        {
            incomeService.deleteIncome(id);
            return ResponseEntity.ok("Income with ID: " + id + " has been deleted !");
        }
        catch(EntityNotFoundException exception)
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(exception.getMessage());
        }
        catch(Exception exception)
        {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exception.getMessage());
        }
    }

    @DeleteMapping("/delete-all")
    public ResponseEntity<?> deleteIncomes()
    {
        incomeService.deleteIncomes();
        return ResponseEntity.ok("All incomes have been deleted !");
    }
}
