package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.BoardRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.service.BoardService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Board Controller")
public class BoardController {

    private final BoardService boardService;

    @Operation(summary = "Create board by workspaceId")
    @PostMapping()
    public ResponseEntity<ApiResponse<Board>> createBoard(@RequestBody @Valid BoardRequest boardRequest){
        ApiResponse<Board> response = ApiResponse.<Board> builder()
                .success(true)
                .message("Created board successfully!")
                .status(HttpStatus.CREATED)
                .payload(boardService.createBoard(boardRequest))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all boards by workspaceId")
    @GetMapping("/{workspace-id}")
    public ResponseEntity<ApiResponse<List<Board>>> getAllBoardsByWorkspaceId(@PathVariable("workspace-id") UUID workspaceId){
        ApiResponse<List<Board>> response = ApiResponse.<List<Board>> builder()
                .success(true)
                .message("Created workspace successfully!")
                .status(HttpStatus.OK)
                .payload(boardService.getAllBoardsByWorkspaceId(workspaceId))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get board by id")
    @GetMapping("{workspace-id}/board-id/{board-id}")
    public ResponseEntity<ApiResponse<Board>> getBoardByWorkspaceIdAndBoardId(@PathVariable("workspace-id") UUID workspaceId, @PathVariable("board-id") UUID boardId){
        ApiResponse<Board> response = ApiResponse.<Board> builder()
                .success(true)
                .message("Get workspace by id successfully!")
                .status(HttpStatus.OK)
                .payload(boardService.getBoardByWorkspaceIdAndBoardId(workspaceId, boardId))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

//    @Operation(summary = "Get workspace by title")
//    @GetMapping("title/{title}")
//    public ResponseEntity<ApiResponse<List<Workspace>>> getWorkspaceByTitle(@PathVariable("title") String title){
//        ApiResponse<List<Workspace>> response = ApiResponse.<List<Workspace>> builder()
//                .success(true)
//                .message("Get workspace by title successfully!")
//                .status(HttpStatus.OK)
//                .payload(workspaceService.getWorkspaceByTitle(title))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
//
//    @Operation(summary = "Edit workspace by id")
//    @PutMapping("/update/{workspace-id}")
//    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceById(@PathVariable("workspace-id") UUID workspaceId, @RequestBody @Valid WorkspaceRequest workspaceRequest){
//        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
//                .success(true)
//                .message("Update workspace by id successfully!")
//                .status(HttpStatus.OK)
//                .payload(workspaceService.updateWorkspaceById(workspaceId, workspaceRequest))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
//
//    @Operation(summary = "Edit workspace by title")
//    @PutMapping("/update/{title}")
//    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceByTitle(@PathVariable("title") String title, @RequestBody @Valid WorkspaceRequest workspaceRequest){
//        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
//                .success(true)
//                .message("Update workspace by title successfully!")
//                .status(HttpStatus.OK)
//                .payload(workspaceService.updateWorkspaceByTitle(title, workspaceRequest))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }
}
