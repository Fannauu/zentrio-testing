package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

public interface WorkspaceService {
    Workspace createWorkspace(WorkspaceRequest workspaceRequest);
}
