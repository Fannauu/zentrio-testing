package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.checkerframework.checker.units.qual.A;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;
import org.example.zentriotesting.model.entity.response.AppUserDTO;
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
    public Workspace createWorkspace(WorkspaceRequest workspaceRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserRepository.getUserByEmail(authentication.getName());
        AppUserDTO userDTO = user.toAppUserDTO(user);
        System.out.println("User Id : " + userDTO.getUserId());

        Workspace create = workspaceRepository.createWorkspace(workspaceRequest, userDTO.getUserId());
        System.out.println("Workspace : " +create);
        return create;
    }

    @Override
    public List<Workspace> getAllWorkspaces() {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserRepository.getUserByEmail(authentication.getName());
//        UserDTO userDTO = user.toDTO(user);
        AppUserDTO userDTO = user.toAppUserDTO(user);
        return workspaceRepository.getAllWorkspaces(userDTO.getUserId());
    }

    @Override
    public Workspace getWorkspaceById(UUID workspaceId) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserRepository.getUserByEmail(authentication.getName());
//        UserDTO userDTO = user.toDTO(user);
        AppUserDTO userDTO = user.toAppUserDTO(user);
        return workspaceRepository.getWorkspaceById(workspaceId, userDTO.getUserId());
    }

    @Override
    public Workspace getWorkspaceByTitle(String title) {

        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserRepository.getUserByEmail(authentication.getName());
//        UserDTO userDTO = user.toDTO(user);
        AppUserDTO userDTO = user.toAppUserDTO(user);
        return workspaceRepository.getWorkspaceByTitle(title, userDTO.getUserId());
    }

    @Override
    public Workspace updateWorkspaceById(UUID workspaceId, WorkspaceRequest workspaceRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserRepository.getUserByEmail(authentication.getName());
//        UserDTO userDTO = user.toDTO(user);
        AppUserDTO userDTO = user.toAppUserDTO(user);
        return workspaceRepository.updateWorkspaceById(workspaceId, workspaceRequest, userDTO.getUserId());
    }

    @Override
    public Workspace updateWorkspaceByTitle(String title, WorkspaceRequest workspaceRequest) {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AppUser user = appUserRepository.getUserByEmail(authentication.getName());
//        UserDTO userDTO = user.toDTO(user);
        AppUserDTO userDTO = user.toAppUserDTO(user);
        return workspaceRepository.updateWorkspaceByTitle(title, workspaceRequest, userDTO.getUserId());
    }


}

