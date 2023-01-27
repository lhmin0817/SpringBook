package com.spring.board;

import java.util.List;

public interface BoardService {

	// 3-1. 글 등록처리 메소드: insertBoard()
	void insertBoard(BoardDTO dto);

	// 3-2. 글 수정처리 메소드: updateBoard()
	void updateBoard(BoardDTO dto);

	// 3-3. 글 제거처리 메소드: deleteBoard()
	void deleteBoard(BoardDTO dto);

	// 3-2. 글 조회처리 메소드: getBoard() : 레코드 1개를 DB에서 select 해서 DTO객체에 담아서 리턴
	BoardDTO getBoard(BoardDTO dto);

	// 3-3. 글 목록조회 처리 메소드: getBoardList()
	List<BoardDTO> getBoardList(BoardDTO dto);

}