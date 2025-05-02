package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.*;
import org.example.zentriotesting.model.entity.request.ManagerRequest;
import org.example.zentriotesting.model.entity.request.MemberRequest;
import org.example.zentriotesting.repository.AppUserRepository;
import org.example.zentriotesting.repository.MemberRepository;
import org.example.zentriotesting.repository.RoleRepository;
import org.example.zentriotesting.repository.WorkspaceRepository;
import org.example.zentriotesting.service.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final AppUserService appUserService;
    private final BoardService boardService;
    private final WorkspaceRepository workspaceRepository;

//    private final AppUserRepository appUserRepository;
    private final RoleService roleService;
    private final RoleRepository roleRepository;

    @Override
    public Member insertManagerToBoard(ManagerRequest managerRequest) {

        UUID userId = appUserService.getCurrentUserId();
        List<Workspace> workspace = workspaceRepository.getAllWorkspaces(userId);
//        UUID workspaceId = workspaceRepository.getWorkspaceById()
//        Board board = boardService



        return memberRepository.insertManagerToBoard(managerRequest);
    }
}
