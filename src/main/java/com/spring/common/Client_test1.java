package com.spring.common;

import java.sql.Connection;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class Client_test1 {
	public static void main(String[] args) {
		
		AbstractApplicationContext factory = new GenericXmlApplicationContext("applicationContext.xml");
		
		Connection conn=null;
		
		//��ü ���� �� �޼ҵ� ȣ�� 
		JDBCUtil db=new JDBCUtil();
		
		conn = db.getConnection();
		//��ü ���� ���� Ŭ���� �̸����� 
		conn= JDBCUtil.getConnection();
		
		
	}

}
