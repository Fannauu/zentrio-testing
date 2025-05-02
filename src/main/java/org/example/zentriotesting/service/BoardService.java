package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.request.BoardRequest;

import java.util.List;
import java.util.UUID;

public interface BoardService {

    Board createBoard(BoardRequest boardRequest);

    List<Board> getAllBoardsByWorkspaceId(UUID workspaceId);

    Board getBoardByWorkspaceIdAndBoardId(UUID workspaceId, UUID boardId);
}
