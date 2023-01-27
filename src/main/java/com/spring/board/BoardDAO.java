package com.spring.board;

import java.beans.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.spring.common.JDBCUtil;

@Repository("boardDAO") // Spring Framework ���� �ڵ����� ��ü�� �����Ǿ RAM �ε�
public class BoardDAO {
	// DAO : Data Access Object -
	// DataBase �� CRUD�ϴ� ��ü : Select, Insert, Update, Delete

	// 1. JDBC ���� ������ ���� : Connection, Statement, PreparedStatement, ResultSet
	private Connection conn = null;
	private Statement stmt = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	// 2. SQL ������ ��� ����� ��Ƽ� ó�� ���� ���� �� �Ҵ� - ����� : ��ü �빮�ڷ� ���
	private final String BOARD_INSERT = "insert into board(seq, title, writer, content)values(select nvl(max(seq),0)+1 from board,?,?,?) ";
	private final String BOARD_UPDATE = "update board set title=?, content=?, where seq=?";
	private final String BOARD_DELETE = "delete board where srq=?";
	private final String BOARD_GET = "select * from board where seq=?"; // DataBase�� ���̺��� 1���� ���ڵ常 ���(�󼼺���)
	private final String BOARD_LIST = "select * from board order by seq desc"; // DataBase�� ���̺��� �������� ���ڵ带
																				// List(ArrayList() )

	// 3. �޼ҵ� :
	// insertBoard(), updateBoard(), deleteBoard() - ���� ���� ����. void
	// getBoard() : BoardDTO�� ��Ƽ� ����, ������ ���ڵ尡 1��
	// getBoardList() : ������ ���ڵ带 DTO(1��), ArrayLIst�� DTO��ü�� ��Ƽ� ����

	// 3-1. �� ���ó�� �޼ҵ�: insertBoard()
	public void insertBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� insertBoard() ���ó�� - ����");
		// Connection ��ü�� ����ؼ� PreparedStatement ��ü Ȱ��ȭ
		try {
			// ������ �߻� �� ���α׷��� ������� �ʵ��� try catch ������� ó��
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_INSERT);

			// pstmt�� ?�� �������� �Ҵ�
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getWriter());
			pstmt.setString(3, dto.getContent());

			pstmt.executeUpdate();

			System.out.println("==> insertBoard() ���ó�� - �Ϸ� ");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> insertBoard() ���ó�� - ���� ");
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	// 3-2. �� ����ó�� �޼ҵ�: updateBoard()
	public void updateBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� updateBoard() ���ó�� - ����");
		try {
			// ��ü ����
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_UPDATE);

			// pstmt�� ? �� dto���� �Ѿ���� ������ �Ҵ�
			pstmt.setString(1, dto.getTitle());
			pstmt.setString(2, dto.getContent());
			pstmt.setInt(3, dto.getSeq());

			pstmt.executeUpdate();
			System.out.println("==> JDBC�� updateBoard() ���ó�� - �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� updateBoard() ���ó�� - ����");
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	// 3-3. �� ����ó�� �޼ҵ�: deleteBoard()
	public void deleteBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� deleteBoard() ���ó�� - ����");
		try {
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_DELETE);

			pstmt.setInt(1, dto.getSeq());

			pstmt.executeUpdate();

			System.out.println("==> JDBC�� deleteBoard() ���ó�� - �Ϸ�");
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("==> JDBC�� deleteBoard() ���ó�� - ����");
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
	}

	// 3-2. �� ��ȸó�� �޼ҵ�: getBoard() : ���ڵ� 1���� DB���� select �ؼ� DTO��ü�� ��Ƽ� ����
	public BoardDTO getBoard(BoardDTO dto) {
		System.out.println("==> JDBC�� getBoard() ���ó��");

		// �������� ������ ���� ���� : try ��� �ۿ��� ����
		BoardDTO board = null;
		try {
			// ��ü ���� : Connection, PreparedStatement
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(BOARD_GET);

			pstmt.setInt(1, dto.getSeq());

			// DB�� select �� ����� rs�� ����
			rs = pstmt.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			JDBCUtil.close(pstmt, conn);
		}
		return board;
	}

	// 3-3. �� �����ȸ ó�� �޼ҵ�: getBoardList()
	public List<BoardDTO> getBoardList(BoardDTO dto) {
		System.out.println("==> JDBC�� getBoardList() ���ó��");
	}
}
