package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Member;
import org.example.zentriotesting.model.entity.request.ManagerRequest;
import org.example.zentriotesting.model.entity.request.MemberRequest;

import java.util.UUID;

public interface MemberService {
    Member insertManagerToBoard(ManagerRequest managerRequest);

//    Member insertManagerToBoard(UUID roleId, UUID boardId);
}
