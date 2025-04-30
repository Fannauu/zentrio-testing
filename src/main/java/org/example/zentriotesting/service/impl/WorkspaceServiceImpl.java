package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;
import org.example.zentriotesting.repository.AppUserRepository;
import org.example.zentriotesting.repository.WorkspaceRepository;
import org.example.zentriotesting.service.WorkspaceService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {
    private final WorkspaceRepository workspaceRepository;
    private final AppUserRepository appUserRepository;


    @Override
    public List<Workspace> getAllWorkspaces() {
        return workspaceRepository.getAllWorkspaces();
    }

    @Override
    public Workspace updateWorkspace(UUID workspaceId, WorkspaceRequest workspaceRequest) {
        return workspaceRepository.updateWorkspace(workspaceRequest);
    }

    @Override
    public Workspace createNewWorkspace(WorkspaceRequest workspaceRequest) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser appUser = appUserRepository.getUserByEmail(authentication.getName());
        System.out.println("Creating new workspace" + workspaceRequest);
        System.out.println("User: " + appUser);
        return workspaceRepository.createNewWorkspace(workspaceRequest, appUser.getUserId());
    }
}
