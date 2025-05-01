package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.*;
import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.request.BoardRequest;

import java.util.UUID;

@Mapper
public interface BoardRepository {

    @Select("""
        INSERT INTO boards(title, description, cover, workspace_id) 
        VALUES (#{request.title}, #{request.description}, #{request.cover}, #{workspaceId})
    """)
    @Results(id = "boardMapper", value = {
            @Result(property = "boardId", column = "board_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "cover", column = "cover"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "isFavourite", column = "is_favourite"),
            @Result(property = "isVerified", column = "is_verified"),
            @Result(property = "workspaceId", column = "workspace_id"),
    })
    Board createBoard(UUID workspaceId, @Param("request") BoardRequest boardRequest, UUID currentUserId);
}
