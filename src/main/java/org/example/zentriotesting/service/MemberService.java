package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.response.Member;

import java.util.UUID;

public interface MemberService {

    Member insertManagerToBoard(UUID managerId, UUID boardId);
}
