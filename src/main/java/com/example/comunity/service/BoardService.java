package com.example.comunity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.example.comunity.domain.Board;
import com.example.comunity.repository.BoardRepository;

import java.util.List;
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    public List<Board> findBoards() {
        return boardRepository.findAll();
    }

    public Board findOne(Long boardId) {

        return boardRepository.findById(boardId).orElseThrow(NullPointerException::new);

    }

    @Transactional
    public void create(Board board) {
        boardRepository.save(board);
    }

    @Transactional
    public void update(Long id, String title, String content,Long hit) {
        Board findBoard = boardRepository.findById(id).orElseThrow(NullPointerException::new); // 영속성 컨텍스트
        findBoard.setTitle(title); // Dirty Checking
        findBoard.setContent(content);
        findBoard.setHit(hit);
    }
    @Transactional
    public void delete(Board board) {
        boardRepository.delete(board);
    }
}