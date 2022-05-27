package com.demo.ebook.utils;

import java.sql.DriverManager;
import com.mysql.jdbc.Connection;
//
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DbUtil {
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://localhost:3306/bookmanager?characterEncoding=utf-8";
	private String dbUserName = "root";
	private String dbPassword = "123456";
	private java.sql.Connection con;

	public Connection getConnection()throws Exception{
	    Class.forName(dbDriver);
		Connection con = (Connection) DriverManager.getConnection(dbUrl,dbUserName,dbPassword);
		return con;
	}

	public void closeCon (Connection con)throws Exception {
		if(con!=null){
			con.close();
		}
	}

	public java.sql.Connection getCon() {
		return con;
	}
}
