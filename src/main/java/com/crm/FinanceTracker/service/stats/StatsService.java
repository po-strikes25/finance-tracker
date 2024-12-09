package com.crm.FinanceTracker.service.stats;

import com.crm.FinanceTracker.dto.GraphDTO;
import com.crm.FinanceTracker.dto.StatsDTO;

public interface StatsService
{
    GraphDTO getChartData();

    StatsDTO getStats();
}
