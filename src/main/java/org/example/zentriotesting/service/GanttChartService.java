package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.GanttChart;
import org.example.zentriotesting.model.entity.request.GanttChartRequest;

public interface GanttChartService {
    GanttChart createGanttChart(GanttChartRequest ganttChartRequest);

}
