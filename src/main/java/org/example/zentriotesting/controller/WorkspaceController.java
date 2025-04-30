package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
@RequestMapping("api/v1/workspaces")
@RequiredArgsConstructor
@SecurityRequirement(name = "bearerAuth")
public class WorkspaceController {
    private final WorkspaceService workspaceService;

    @GetMapping
    public ResponseEntity<ApiResponse<List<Workspace>>> getAllWorkspaces() {
        ApiResponse<List<Workspace>> apiResponse = ApiResponse.<List<Workspace>>builder()
                .success(true)
                .message("Get all workspaces successfully")
                .payload(workspaceService.getAllWorkspaces())
                .status(HttpStatus.OK)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }


    @PostMapping
    public ResponseEntity<ApiResponse<Workspace>> createNewWorkspace(@RequestBody WorkspaceRequest workspaceRequest) {
        ApiResponse<Workspace> apiResponse = ApiResponse.<Workspace>builder()
                .success(true)
                .message("Workspace created successfully")
                .payload(workspaceService.createNewWorkspace(workspaceRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }

    @PutMapping("/{workspace-id}")
    public ResponseEntity<ApiResponse<Workspace>> updateWorkspace(@PathVariable("workspace-id") UUID workspaceId , @RequestBody WorkspaceRequest workspaceRequest) {
        ApiResponse<Workspace> apiResponse = ApiResponse.<Workspace>builder()
                .success(true)
                .message("Workspace created successfully")
                .payload(workspaceService.updateWorkspace(workspaceId,workspaceRequest))
                .status(HttpStatus.CREATED)
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(apiResponse);
    }


}
