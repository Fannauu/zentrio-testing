package org.example.zentriotesting.service;

import org.example.zentriotesting.model.entity.Board;
import org.example.zentriotesting.model.entity.request.BoardRequest;

import java.util.UUID;

public interface BoardService {

    Board createBoard(BoardRequest boardRequest);
}
