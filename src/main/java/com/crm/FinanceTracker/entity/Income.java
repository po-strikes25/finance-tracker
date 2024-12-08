package com.crm.FinanceTracker.entity;

import com.crm.FinanceTracker.dto.IncomeDTO;
import com.crm.FinanceTracker.enums.IncomeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Income
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long        incomeId;
    private String      incomeTitle;
    private String      description;
    private IncomeType  incomeType;
    private LocalDate   date;
    private Double      amount;

    public IncomeDTO getIncomeDTO()
    {
        IncomeDTO incomeDTO = new IncomeDTO();

        incomeDTO.setId(incomeId);
        incomeDTO.setTitle(incomeTitle);
        incomeDTO.setDescription(description);
        incomeDTO.setIncomeType(incomeType);
        incomeDTO.setDate(date);
        incomeDTO.setAmount(amount);

        return incomeDTO;
    }
}
