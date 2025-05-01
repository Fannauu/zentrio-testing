package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.exception.NotFoundException;
import org.example.zentriotesting.model.entity.AppUser;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.Workspace;
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
    public Board createBoard(BoardRequest boardRequest) {

        UUID currentUserId = appUserService.getCurrentUserId();
        Workspace workspaceById = workspaceRepository.getWorkspaceById(boardRequest.getWorkspaceId(), currentUserId);
        System.out.println("Workspace in Board : " + workspaceById);
        System.out.println("Get workspace id by current user : " + workspaceById.getWorkspaceId());
        UUID requestWorkspaceId = boardRequest.getWorkspaceId();
        System.out.println("Workspace Id : " + requestWorkspaceId);
        //        UUID getWorkspaceId = workspaceRepository.getWorkspaceIdByCurrentUser(currentUserId);
        if ( !requestWorkspaceId.equals(workspaceById.getWorkspaceId())){
            throw new NotFoundException("Workspace Id not found");
        }else {
            boardRequest.setIsVerified(true);
        }

        return boardRepository.createBoard(boardRequest);
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
