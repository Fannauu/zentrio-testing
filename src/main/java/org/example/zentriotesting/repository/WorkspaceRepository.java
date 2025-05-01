package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.*;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

import java.util.List;
import java.util.UUID;

@Mapper
public interface WorkspaceRepository {

    @Select("""
        INSERT INTO workspaces(title, description, created_by) 
        VALUES (#{request.title}, #{request.description}, #{userId})
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
    Workspace createWorkspace(@Param("request") WorkspaceRequest workspaceRequest, UUID userId);

    @Select("""
        SELECT * FROM workspaces WHERE created_by = #{userId}
    """)
    @ResultMap("workspaceMapper")
    List<Workspace> getAllWorkspaces(UUID userId);

    @Select("""
        SELECT * FROM workspaces WHERE workspace_id = #{workspaceId} AND created_by = #{userId}
    """)
    @ResultMap("workspaceMapper")
    Workspace getWorkspaceById(UUID workspaceId, UUID userId);

    @Select("""
        SELECT * FROM workspaces WHERE title ILIKE '%'||#{title}||'%' AND created_by = #{userId}
    """)
    @ResultMap("workspaceMapper")
    Workspace getWorkspaceByTitle(String title, UUID userId);

    @Select("""
        UPDATE workspaces 
        SET title = #{request.title}, description = #{request.description}
        WHERE workspace_id = #{workspaceId} AND created_by = #{userId}
    """)
    @ResultMap("workspaceMapper")
    Workspace updateWorkspaceById(UUID workspaceId, WorkspaceRequest workspaceRequest, UUID userId);

    @Select("""
        UPDATE workspaces 
        SET title = #{request.title}, description = #{request.description}
        WHERE title ILIKE '%' || #{title} || '%' AND created_by = #{userId}
    """)
    @ResultMap("workspaceMapper")
    Workspace updateWorkspaceByTitle(String title, @Param("request") WorkspaceRequest workspaceRequest, UUID userId);

    @Select("""
        SELECT workspaces.workspace_id FROM workspaces WHERE created_by = #{userId}
    """)
    @ResultMap("workspaceMapper")
    UUID getWorkspaceIdByCurrentUser(UUID userId);

}

