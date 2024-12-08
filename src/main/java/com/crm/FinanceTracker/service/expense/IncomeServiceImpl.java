package com.crm.FinanceTracker.service.expense;

import com.crm.FinanceTracker.dto.IncomeDTO;
import com.crm.FinanceTracker.entity.Income;
import com.crm.FinanceTracker.repo.IncomeRepository;
import com.crm.FinanceTracker.service.expense.IncomeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService
{
    private final IncomeRepository incomeRepository;

    @Override
    public Income postIncome(IncomeDTO incomeDTO)
    {
        return saveOrUpdate(new Income(), incomeDTO);
    }

    private Income saveOrUpdate(Income income, IncomeDTO incomeDTO)
    {
        income.setIncomeTitle(incomeDTO.getTitle());
        income.setDescription(incomeDTO.getDescription());
        income.setIncomeType(incomeDTO.getIncomeType());
        income.setDate(incomeDTO.getDate());
        income.setAmount(incomeDTO.getAmount());

        return incomeRepository.save(income);
    }

    @Override
    public List<IncomeDTO> getAllIncomes()
    {
        return findAllIncomes();
    }

    private List<IncomeDTO> findAllIncomes()
    {
        return incomeRepository.findAll().stream()
                .sorted(Comparator.comparing(Income::getDate).reversed())
                .map(Income::getIncomeDTO)
                .collect(Collectors.toList());
    }

    @Override
    public IncomeDTO getIncomeByID(Long incomeId)
    {
        return fetchIncomeByID(incomeId);
    }

    private IncomeDTO fetchIncomeByID(Long incomeId)
    {
        Optional<Income> incomeOptional = incomeRepository.findById(incomeId);

        if(incomeOptional.isPresent())
        {
            return incomeOptional.get().getIncomeDTO();
        }
        else
        {
            throw new EntityNotFoundException("Income with ID: \" + incomeId + \" not found !");
        }
    }

    @Override
    public Income updateIncomeByID(Long incomeId, IncomeDTO incomeDTO)
    {
        return putIncomeByID(incomeId, incomeDTO);
    }

    private Income putIncomeByID(Long incomeId, IncomeDTO incomeDTO)
    {
        Optional<Income> incomeOptional = incomeRepository.findById(incomeId);

        if(incomeOptional.isPresent())
        {
            return saveOrUpdate(incomeOptional.get(), incomeDTO);
        }
        else
        {
            throw new EntityNotFoundException("Income with ID: " + incomeId + " not found !");
        }
    }

    @Override
    public void deleteIncome(Long id)
    {
        deleteIncomeByID(id);
    }

    private void deleteIncomeByID(Long id)
    {
        Optional<Income> optionalExpense = incomeRepository.findById(id);

        if(optionalExpense.isPresent())
        {
            incomeRepository.deleteById(id);
        }
        else
        {
            throw new EntityNotFoundException("Income with ID: " + id + " not found !");
        }
    }

    @Override
    public void deleteIncomes()
    {
        deleteAllIncomes();
    }

    private void deleteAllIncomes()
    {
        incomeRepository.deleteAll();
    }
}
