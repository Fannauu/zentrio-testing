package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    Workspace createWorkspace(WorkspaceRequest workspaceRequest);

    List<Workspace> getAllWorkspaces();

    List<Workspace> getWorkspaceByTitle(String title);

    Workspace getWorkspaceById(UUID workspaceId);

    Workspace updateWorkspaceById(UUID workspaceId, WorkspaceRequest workspaceRequest);

    Workspace updateWorkspaceTitleByWorkspaceId(UUID workspaceId, String title);

    Workspace updateWorkspaceDescriptionByWorkspaceId(UUID workspaceId, String description);

    UUID checkExistedWorkspaceId(UUID existedWorkspaceId);
}
