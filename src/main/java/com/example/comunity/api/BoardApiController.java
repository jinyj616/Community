package com.example.comunity.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.comunity.domain.Board;
import com.example.comunity.dto.BoardDeleteDto;
import com.example.comunity.dto.BoardDto;
import com.example.comunity.service.BoardService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.lang.Integer.parseInt;

@RestController
@RequiredArgsConstructor
public class BoardApiController {

    private final BoardService boardService; // Autowired로 스프링 빈에 등록

    @GetMapping("/api/board-list")
    public WrapperClass board_list(){
        List<Board> boardList = boardService.findBoards();
        List<BoardDto> boardDtoList = boardList.stream().map(b -> new BoardDto(b)).collect(Collectors.toList());
        return new WrapperClass(boardDtoList);
    }
    @GetMapping("/api/board-detail/{boardId}")
    public WrapperClass board_detail(@PathVariable("boardId") Long boardId){

            Board board = boardService.findOne(boardId);
            BoardDto boardDto = new BoardDto(board);
        try {
            boardService.update(boardDto.getId(), boardDto.getTitle(), boardDto.getContent(), boardDto.getHit()+1);

        }catch (Exception exception) {

        }
        return new WrapperClass(boardDto);
    }

    @PostMapping("/api/create-board")
    public ResponseEntity create_board(@RequestBody BoardDto boardDto){
        System.out.println("create_board/boardDto = " + boardDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.CREATED; // 201 잘 생성되었음을 의미
        try{
            Board board = new Board(
                    boardDto.getId(),
                    boardDto.getTitle(),
                    boardDto.getContent(),
                    0L
            );
            boardService.create(board);
        } catch (Exception exception){
            status = HttpStatus.BAD_REQUEST; // 400 에러
            System.out.println("create_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }
    @PutMapping("/api/update-board")
    public ResponseEntity update_board(@RequestBody BoardDto boardDto) {
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT;

        try {
            // 데이터베이스에서 기존 게시물을 가져옵니다.
            Board existingBoard = boardService.findOne(boardDto.getId());

            if (existingBoard != null) {
                // 조회수를 초기화하지 않고 제목과 내용만 업데이트합니다.
                boardService.update(boardDto.getId(), boardDto.getTitle(), boardDto.getContent(), existingBoard.getHit());
            } else {
                status = HttpStatus.NOT_FOUND;
            }
        } catch (Exception exception) {
            status = HttpStatus.BAD_REQUEST;
            System.out.println("update_board/exception = " + exception);
        }

        return new ResponseEntity(body, headers, status);
    }



    @DeleteMapping("/api/delete-board")
    public ResponseEntity delete_board(@RequestBody BoardDeleteDto boardDeleteDto){
        System.out.println("delete_board/boardDeleteDto = " + boardDeleteDto);
        HttpHeaders headers = new HttpHeaders();
        Map<String, String> body = new HashMap<>();
        HttpStatus status = HttpStatus.NO_CONTENT; // 204
        try{
            Board board = boardService.findOne(boardDeleteDto.getId());
            boardService.delete(board);
        } catch (Exception exception){
            status = HttpStatus.BAD_REQUEST;
            System.out.println("delete_board/exception = " + exception);
        }
        return new ResponseEntity(body, headers, status);
    }
}

