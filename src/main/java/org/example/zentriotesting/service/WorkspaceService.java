package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {

    Workspace createWorkspace(WorkspaceRequest workspaceRequest);

    List<Workspace> getAllWorkspaces();

    Workspace getWorkspaceByTitle(String title);

    Workspace updateWorkspaceByTitle(String title, WorkspaceRequest workspaceRequest);

    Workspace getWorkspaceById(UUID workspaceId);

    Workspace updateWorkspaceById(UUID workspaceId, WorkspaceRequest workspaceRequest);
}
