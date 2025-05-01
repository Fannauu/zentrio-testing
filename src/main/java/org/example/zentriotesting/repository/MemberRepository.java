package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.example.zentriotesting.model.entity.response.Member;

import java.util.UUID;

@Mapper
public interface MemberRepository {

    @Select("""
        INSERT INTO members(role_id, user_id, board_id) VALUES 
    """)
    Member insertManagerToBoard(UUID managerId, UUID boardId);
}
