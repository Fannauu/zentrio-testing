package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;
import org.example.zentriotesting.repository.WorkspaceRepository;
import org.example.zentriotesting.service.AppUserService;
import org.example.zentriotesting.service.WorkspaceService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class WorkspaceServiceImpl implements WorkspaceService {

    private final AppUserService appUserService;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public Workspace createWorkspace(WorkspaceRequest workspaceRequest) {

//        Workspace createWorkspace = workspaceRepository.createWorkspace(appUserService.currentId(), LocalDateTime.now(), workspaceRequest);

        return workspaceRepository.createWorkspace(appUserService.currentId(), LocalDateTime.now(), workspaceRequest);
    }
}
