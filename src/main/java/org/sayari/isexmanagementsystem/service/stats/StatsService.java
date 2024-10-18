package org.sayari.isexmanagementsystem.service.stats;

import lombok.RequiredArgsConstructor;
import org.sayari.isexmanagementsystem.dto.GraphDTo;
import org.sayari.isexmanagementsystem.dto.StatsDto;
import org.sayari.isexmanagementsystem.entity.Income;
import org.springframework.stereotype.Service;


public interface StatsService {
    GraphDTo getChartData();
    StatsDto getStats();
}
