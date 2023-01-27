package com.spring.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class JDBCUtil {
		//DB를 연결 설정하는 클래스
	//1. DB를 연결하는 메소드 
		//static : 객체 생성없이 클래스 이름으로 바로 호출
	public static Connection getConnection() {
		//String driver="oracle.jdbc.driver.OracleDriver";
		String driver="org.h2.Driver";
		//String url="jdbc:oracle:thin:@localhost:1521:XE";
		String url="jdbc:h2:tcp://localhost/~/test2";
		Connection conn=null;
		
		try {
			Class.forName(driver);
			//conn=DriverManager.getConnection(url, "C##HR","1234");
			conn=DriverManager.getConnection(url, "sa","");
			System.out.println("Oracle DB에 잘 연결되었습니다."); //확인 후 주석처리
			return conn;
		}catch(Exception e) {
			e.printStackTrace();
			System.out.println("Oracle DB연결에 실패 했습니다.");
		}
		return null;
		
	}
	
	//2. DB연결을 제거하는 메소드 : Connection, PreparedStatement 객체를 제거
		//Insert, Update, Delete
	public static void close(PreparedStatement pstmt, Connection conn) {
		if(pstmt !=null) {
			try {
				if(!pstmt.isClosed()) {	//pstmt 객체가 제거되지 않은 상태라면
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				pstmt=null;
			}
		}
		if(conn !=null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				conn=null;
			}
			
		}
		
	}
	//3. DB연결을 제거하는 메소드 : Connection, PreparedStatement, ResultSet 객체를 제거
		//Select 
	public static void close(PreparedStatement pstmt, Connection conn, ResultSet rs) {
		if(pstmt !=null) {
			try {
				if(!pstmt.isClosed()) {	//pstmt 객체가 제거되지 않은 상태라면
					pstmt.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				pstmt=null;
			}
		}
		if(conn !=null) {
			try {
				if(!conn.isClosed()) {
					conn.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				conn=null;
			}
			
		}
		if(rs !=null) {
			try {
				if(!rs.isClosed()) {
					rs.close();
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				rs=null;
			}
			
		}
	}
}
