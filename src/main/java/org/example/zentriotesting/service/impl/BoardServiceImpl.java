package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.request.BoardRequest;
import org.example.zentriotesting.repository.AppUserRepository;
import org.example.zentriotesting.repository.BoardRepository;
import org.example.zentriotesting.repository.WorkspaceRepository;
import org.example.zentriotesting.service.AppUserService;
import org.example.zentriotesting.service.BoardService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class BoardServiceImpl implements BoardService {

    private final BoardRepository boardRepository;
    private final AppUserService appUserService;
    private final AppUserRepository appUserRepository;
    private final WorkspaceRepository workspaceRepository;

    @Override
    public Board createBoard(UUID workspaceId, BoardRequest boardRequest) {

        UUID currentUserId = appUserService.getCurrentUserId();
//        UUID getWorkspaceId = workspaceRepository.getWorkspaceIdByCurrentUser(currentUserId);
        System.out.println(currentUserId);
      boardRepository.createBoard(workspaceId, boardRequest);

        return null;
    }

//    public Boolean isVerifeied(){
//
//        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//        AppUser appUser = appUserRepository.getUserByEmail(authentication.getName());
//        UUID currentUserId = appUserService.getCurrentUserId();
//
//        if (currentUserId == appUser.getUserId()){
//
//        }
//        return null;
//    }
}
