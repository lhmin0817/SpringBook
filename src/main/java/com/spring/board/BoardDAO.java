package com.spring.board;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.spring.common.JDBCUtil;

@Repository("boardDAO") // Spring Framework 에서 자동으로 객체가 생성되어서 RAM 로드
public class BoardDAO {
	// DAO : Data Access Object -
	// DataBase 에 CRUD하는 객체 : Select, Insert, Update, Delete

	// 1. JDBC 관련 변수를 선언 : Connection, Statement, PreparedStatement, ResultSet
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 2. SQL 쿼리를 담는 상수에 담아서 처리 변수 생성 후 할당 - 상수명 : 전체 대문자로 사용
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content)values(select nvl(max(seq),0)+1 from board,?,?,?) ";
	private final String BOARD_UPDATE = "update board set title=?, content=?, where seq=?";
	private final String BOARD_DELETE = "delete board where srq=?";
	private final String BOARD_GET = "select * from board where seq=?"; // DataBase의 테이블에서 1개의 레코드만 출력(상세보기)
	private final String BOARD_LIST = "select * from board order by seq desc"; // DataBase의 테이블의 여러개의 레코드를
																				// List(ArrayList() )

	// 3. 메소드 :
	// insertBoard(), updateBoard(), deleteBoard() - 리턴 값이 없다. void
	// getBoard() : BoardDTO에 담아서 전송, 가져온 레코드가 1개
	// getBoardList() : 각각의 레코드를 DTO(1개), ArrayLIst에 DTO객체를 담아서 리턴

	// 3-1. 글 등록처리 메소드: insertBoard()
	public void insertBoard(BoardDTO dto) {
		System.out.println("==> JDBC로 insertBoard() 기능처리 - 시작");
		// Connection 객체를 사용해서 PreparedStatement 객체 활성화
		try {
			// 오류가 발생 시 프로그램이 종료되지 않도록 try catch 블락으로 처리
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);

			// pstmt에 ?에 변수값을 할당
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());

			pstmt.executeUpdate();

			System.out.println("==> insertBoard() 기능처리 - 완료 ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> insertBoard() 기능처리 - 실패 ");
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	// 3-2. 글 수정처리 메소드: updateBoard()
	public void updateBoard(BoardDTO dto) {
		System.out.println("==> JDBC로 updateBoard() 기능처리 - 시작");
		try {
			// 객체 생성
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);

			// pstmt의 ? 에 dto에서 넘어오는 변수값 할당
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());

			pstmt.executeUpdate();
			System.out.println("==> JDBC로 updateBoard() 기능처리 - 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 updateBoard() 기능처리 - 실패");
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	// 3-3. 글 제거처리 메소드: deleteBoard()
	public void deleteBoard(BoardDTO dto) {
		System.out.println("==> JDBC로 deleteBoard() 기능처리 - 시작");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);

			pstmt.setInt(1, dto.getSeq());

			pstmt.executeUpdate();

			System.out.println("==> JDBC로 deleteBoard() 기능처리 - 완료");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 deleteBoard() 기능처리 - 실패");
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	// 3-2. 글 조회처리 메소드: getBoard() : 레코드 1개를 DB에서 select 해서 DTO객체에 담아서 리턴
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("==> JDBC로 getBoard() 기능처리");

		// 리턴으로 돌려줄 변수 선언 : try 블락 밖에서 선언
		BoardDTO board = null;
		try {
			// 객체 생성 : Connection, PreparedStatement
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);

			pstmt.setInt(1, dto.getSeq());

			// DB를 select 한 결과를 rs에 저장
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		return board;
	}

	// 3-3. 글 목록조회 처리 메소드: getBoardList()
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		System.out.println("==> JDBC로 getBoardList() 기능처리");
	}
}
