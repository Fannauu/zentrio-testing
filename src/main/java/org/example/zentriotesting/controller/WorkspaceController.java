package org.example.zentriotesting.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;
import org.example.zentriotesting.model.entity.response.ApiResponse;
import org.example.zentriotesting.service.WorkspaceService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/v1/workspaces")
@RequiredArgsConstructor
@Tag(name = "Workspace Controller")
public class WorkspaceController {

    private final WorkspaceService workspaceService;

    @Operation(summary = "Create workspace")
    @PostMapping()
    public ResponseEntity<ApiResponse<Workspace>> createWorkspace(@RequestBody WorkspaceRequest workspaceRequest){
        ApiResponse<Workspace> response = ApiResponse.<Workspace>builder()
                .success(true)
                .message("Create workspace successfully")
                .status(HttpStatus.CREATED)
                .payload(workspaceService.createWorkspace(workspaceRequest))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }


}
