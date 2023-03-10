package com.spring.board;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.spring.common.JDBCUtil;

@Repository("boardDAO") // Spring Framework 에서 자동으로 객체가 생성되어서 RAM 로드
public class BoardDAO implements BoardService {
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
	@Override
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
	@Override
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
	@Override
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
	@Override
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("==> JDBC로 getBoard() 기능처리 - 시작");

		// 리턴으로 돌려줄 변수 선언 : try 블락 밖에서 선언
		BoardDTO board = null;
		try {
			// 객체 생성 : Connection, PreparedStatement
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);

			pstmt.setInt(1, dto.getSeq());

			// DB를 select 한 결과를 rs에 저장
			rs = pstmt.executeQuery();
			
			//rs에 담긴 값을 DTO에 저장해서 리턴으로 돌려줌
			if(rs.next()) { //rs의 값이 존재한다면, rs의 값을 DTO에 담아서 리턴
				board.setSeq(rs.getInt("SEQ"));
				board.setTitle(rs.getString("TITLE"));
				board.setWriter(rs.getString("WRITER"));
				board.setContent(rs.getString("CONTENT"));
				board.setRegDate(rs.getDate("REGDATE"));
				board.setCnt(rs.getInt("CNT"));
			}else {
				System.out.println("레코드의 결과가 없습니다.");
			}
			System.out.println("==> JDBC로 getBoard() 기능처리 - 완료");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 getBoard() 기능처리 - 실패");
		} finally {
			JDBCUtil.close(pstmt, conn, rs);
		}
		return board;
	}

	// 3-3. 글 목록조회 처리 메소드: getBoardList()
	@Override
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		System.out.println("==> JDBC로 getBoardList() 기능처리 - 시작");
		//리턴 돌려줄 변수 선언 : List <= 인터페이스
			//ArrayList, Vector, LinkedList <== List 인터페이스를 구현한 클래스
				//ArrayList : 싱글 쓰레드 환경
				//Vector : 멀티 쓰레드 환경
				//LinkedList : 자주 수정, 삭제 시 성능이 빠르게 처리
		List<BoardDTO> boardList=new ArrayList<BoardDTO>();
		BoardDTO board=null;
		try {
			conn=JDBCUtil.getConnection();
			pstmt=conn.prepareStatement(BOARD_LIST);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				do {
					//rs에서 가져온 1개의 레코드르 board(DTO)
					board.setSeq(rs.getInt("SEQ"));
					board.setTitle(rs.getString("TITLE"));
					board.setWriter(rs.getString("WRITER"));
					board.setContent(rs.getString("CONTENT"));
					board.setRegDate(rs.getDate("REGDATE"));
					board.setCnt(rs.getInt("CNT"));
					
					//boardList : ARrauList에 add()메소드를 사용해서 board(DTO)를 저장
					boardList.add(board);
				}while(rs.next());
			}else {
				System.out.println("테이블에 레코드가 비어 있습니다.");
			}
			System.out.println("==> JDBC로 getBoardList() 기능처리 - 완료");
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC로 getBoardList() 기능처리 - 실패");
		}finally {
			JDBCUtil.close(pstmt, conn, rs);
		}
		
		return boardList;
	}
}
