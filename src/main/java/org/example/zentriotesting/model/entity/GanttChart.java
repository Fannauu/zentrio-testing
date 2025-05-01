package org.example.zentriotesting.model.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GanttChart {
    private UUID ganttChartId;
    private String title;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Board board_id;
}
