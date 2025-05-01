package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
@Tag(name = "Workspace Controller")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Operation(summary = "Create workspace")
    @PostMapping()
    public ResponseEntity<ApiResponse<Workspace>> createWorkspace(@RequestBody WorkspaceRequest workspaceRequest){
        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
                .success(true)
                .message("Created workspace successfully!")
                .status(HttpStatus.CREATED)
                .payload(workspaceService.createWorkspace(workspaceRequest))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all workspaces")
    @GetMapping()
    public ResponseEntity<ApiResponse<List<Workspace>>> getAllWorkspaces(){
        ApiResponse<List<Workspace>> response = ApiResponse.<List<Workspace>> builder()
                .success(true)
                .message("Created workspace successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.getAllWorkspaces())
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get workspace by id")
    @GetMapping("workspace-id/{workspace-id}")
    public ResponseEntity<ApiResponse<Workspace>> getWorkspaceById(@PathVariable("workspace-id") UUID workspaceId){
        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
                .success(true)
                .message("Get workspace by id successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.getWorkspaceById(workspaceId))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get workspace by title")
    @GetMapping("title/{title}")
    public ResponseEntity<ApiResponse<Workspace>> getWorkspaceByTitle(@PathVariable("title") String title){
        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
                .success(true)
                .message("Get workspace by title successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.getWorkspaceByTitle(title))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edit workspace by id")
    @PutMapping("/update/{workspace-id}")
    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceById(@PathVariable("workspace-id") UUID workspaceId, @RequestBody WorkspaceRequest workspaceRequest){
        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
                .success(true)
                .message("Update workspace by id successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.updateWorkspaceById(workspaceId, workspaceRequest))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edit workspace by title")
    @PutMapping("/update/{title}")
    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceByTitle(@PathVariable("title") String title, @RequestBody WorkspaceRequest workspaceRequest){
        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
                .success(true)
                .message("Update workspace by title successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.updateWorkspaceByTitle(title, workspaceRequest))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

//    @Operation(summary = "Edit workspace fields")
//    @PatchMapping("/update")
//    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceField(@PathVariable String title, @RequestBody WorkspaceRequest workspaceRequest){
//        ApiResponse<Workspace> response = ApiResponse.<Workspace> builder()
//                .success(true)
//                .message("Update workspace by title successfully!")
//                .httpStatus(HttpStatus.OK)
//                .payload(workspaceService.updateWorkspaceByTitle(title, workspaceRequest))
//                .timestamp(LocalDateTime.now())
//                .build();
//
//        return ResponseEntity.ok(response);
//    }




}