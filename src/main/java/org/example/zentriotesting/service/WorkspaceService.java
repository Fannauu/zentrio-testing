package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

import java.util.List;
import java.util.UUID;

public interface WorkspaceService {
    Workspace createNewWorkspace(WorkspaceRequest workspaceRequest);

    List<Workspace> getAllWorkspaces();

    Workspace updateWorkspace(UUID workspaceId , WorkspaceRequest workspaceRequest);

}
