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

    @Operation(summary = "Get All Workspace")
    @GetMapping("/get-all-worksapces")
    public ResponseEntity<ApiResponse<List<Workspace>>> getAllWorkspaces() {
        ApiResponse<List<Workspace>> response = ApiResponse.<List<Workspace>>builder()
                .success(true)
                .payload(workspaceService.getAllWorkspaces())
                .timestamp(LocalDateTime.now())
                .build();
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get workspace by id")
    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<Workspace>> getWorkspaceById(@PathVariable UUID workspaceId) {
        ApiResponse<Workspace> response = ApiResponse.<Workspace>builder()
                .success(true)
                .message("Get workspace by id successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.getWorkspaceById(workspaceId))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get workspace by title")
    @GetMapping("/{title}")
    public ResponseEntity<ApiResponse<Workspace>> getWorkspaceByTitle(@PathVariable String title) {
        ApiResponse<Workspace> response = ApiResponse.<Workspace>builder()
                .success(true)
                .message("Get workspace by title successfully!")
                .status(HttpStatus.OK)
                .payload(workspaceService.getWorkspaceByTitle(title))
                .timestamp(LocalDateTime.now())
                .build();

        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Edit workspace by id")
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceById(@PathVariable("id") UUID workspaceId, @RequestBody WorkspaceRequest workspaceRequest) {
        ApiResponse<Workspace> response = ApiResponse.<Workspace>builder()
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
    public ResponseEntity<ApiResponse<Workspace>> updateWorkspaceByTitle(@PathVariable String title, @RequestBody WorkspaceRequest workspaceRequest) {
        ApiResponse<Workspace> response = ApiResponse.<Workspace>builder()
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

