package org.example.zentriotesting.service.impl;

import lombok.RequiredArgsConstructor;
import org.example.zentriotesting.model.entity.response.Member;
import org.example.zentriotesting.repository.MemberRepository;
import org.example.zentriotesting.service.MemberService;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Member insertManagerToBoard(UUID managerId, UUID boardId) {
        return memberRepository.insertManagerToBoard(managerId, boardId);
    }
}
