package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.GanttChart;
import org.example.zentriotesting.model.entity.request.BoardRequest;
import org.example.zentriotesting.model.entity.request.GanttChartRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Board Controller")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "Create board by workspaceId")
    @PostMapping("/{workspace-id}")
    public ResponseEntity<ApiResponse<Board>> createBoard(@PathVariable("workspace-id") UUID workspaceId, @RequestBody BoardRequest boardRequest){
        ApiResponse<Board> response = ApiResponse.<Board> builder()
                .success(true)
                .message("Created board successfully!")
                .status(HttpStatus.CREATED)
                .payload(boardService.createBoard(workspaceId, boardRequest))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }
}
