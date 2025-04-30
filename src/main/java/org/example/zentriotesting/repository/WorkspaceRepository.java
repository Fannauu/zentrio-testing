package org.example.zentriotesting.repository;

import org.apache.ibatis.annotations.*;
import org.example.zentriotesting.model.entity.Workspace;
import org.example.zentriotesting.model.entity.request.WorkspaceRequest;

import java.util.List;
import java.util.UUID;

@Mapper
public interface WorkspaceRepository {
    @Results(id = "workspaceMapper", value = {
            @Result(property = "workspaceId",column = "workspace_id"),
            @Result(property = "createdAt",column = "created_at"),
            @Result(property = "updatedAt",column = "updated_at"),
            @Result(property = "userId",column = "created_by")

    })
    @Select("""
        INSERT INTO workspaces(title,description,created_by)
        VALUES (#{req.title},#{req.description},#{createdBy})
        RETURNING *
    """)
    Workspace createNewWorkspace(@Param("req") WorkspaceRequest workspaceRequest, UUID createdBy);

    @Select("""
       SELECT * FROM workspaces;
    """)
    @ResultMap("workspaceMapper")
    List<Workspace> getAllWorkspaces();


    @Select("""
        UPDATE workspaces set title = #{req.title},description = #{req.description}
        RETURNING *
    """)
    @ResultMap("workspaceMapper")
    Workspace updateWorkspace(@Param("req") WorkspaceRequest workspaceRequest);
}
