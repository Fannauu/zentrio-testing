package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

import java.time.LocalDateTime;
import java.util.UUID;

@Mapper
public interface WorkspaceRepository {

    @Select("""
        INSERT INTO workspaces(title, description, created_at, created_by) 
        VALUES (#{request.title}, #{request.description},#{createdAt}, #{userId})
        RETURNING *
    """)
    @Results(id = "workspaceMapper", value = {
            @Result(property = "workspaceId", column = "workspace_id"),
            @Result(property = "title", column = "title"),
            @Result(property = "description", column = "description"),
            @Result(property = "createdAt", column = "created_at"),
            @Result(property = "updatedAt", column = "updated_at"),
            @Result(property = "createdBy", column = "created_by"),
    })
    Workspace createWorkspace(UUID userId, LocalDateTime createdAt, WorkspaceRequest workspaceRequest);
}
