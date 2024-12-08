package com.crm.FinanceTracker.controller;

import com.crm.FinanceTracker.dto.ExpenseDTO;
import com.crm.FinanceTracker.entity.Expense;
import com.crm.FinanceTracker.service.income.ExpenseService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expense")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ExpenseController
{
    private final ExpenseService expenseService;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAllExpenses()
    {
        /*
        Optional<List<Expense>> optionalExpenses = Optional.ofNullable(expenseService.getAllExpenses());

        return optionalExpenses
                .map(expense -> ResponseEntity.status(HttpStatus.FOUND).body(expense))
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
        */
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getExpenseByID(@PathVariable Long id)
    {
        try
        {
            return ResponseEntity.ok(expenseService.getExpenseByID(id));
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
    public ResponseEntity<?> updateExpenseByID(@PathVariable Long id, @RequestBody ExpenseDTO expenseDTO)
    {
        try
        {
            return ResponseEntity.ok(expenseService.updateExpenseByID(id, expenseDTO));
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
    public ResponseEntity<?> postExpense(@RequestBody ExpenseDTO expenseDTO)
    {
        Expense createdExpense = expenseService.postExpense(expenseDTO);

        if(createdExpense != null)
        {
            return ResponseEntity.status(HttpStatus.CREATED).body(createdExpense);
        }
        else
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteExpenseByID(@PathVariable Long id)
    {
        try
        {
            expenseService.deleteExpense(id);
            return ResponseEntity.ok("Expense with ID: " + id + " has been deleted !");
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
    public ResponseEntity<?> deleteExpenses()
    {
        expenseService.deleteExpenses();
        return ResponseEntity.ok("All expenses have been deleted !");
    }
}
