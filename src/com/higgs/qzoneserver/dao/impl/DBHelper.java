package com.higgs.qzoneserver.dao.impl;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class DBHelper {

//public static void main(String[] args) throws SQLException {
//		getConnection();
//	}


	public static Connection getConnection() {

		Connection oConnection=null;
		try {
//			Context oContext = new InitialContext();
//			DataSource oDataSource = (DataSource)oContext.lookup("java:comp/env/jdbc/myqzone");
//			oConnection = oDataSource.getConnection();

			//数据库用户名
			 String user = "root";
			//数据库密码
			 String password = "root123";
			//驱动信息
			 String DRIVER = "com.mysql.jdbc.Driver";
			//数据库地址
			 String url = "jdbc:mysql://localhost:3306/myqzone";
			 //加载驱动
			Class.forName(DRIVER);
			System.out.println("已连接数据库");

//			String url = "jdbc:mysql://localhost:3306/myqzone?&useSSL=false";
//			String user = "root";
//			String password = "root123";
			oConnection = DriverManager.getConnection(url, user, password);
//			String sql = "select * from user";
//			Statement stmt = oConnection.createStatement();
//			ResultSet rs = stmt.executeQuery(sql);
//			while (rs.next()) {
//				System.out.println(rs.getInt(1) + " " + rs.getString(2));
//			}
//			rs.close();
//			stmt.close();
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("异常");
		}
		return oConnection;
	}
	public static void	 closeConection(Connection oConnection,Statement oStatement,ResultSet oResultSet) {
		try {
			if (oResultSet!=null) {
				oResultSet.close();
			}
			if (oStatement!=null) {
				oStatement.close();
			}
			if (oConnection!=null&&!oConnection.isClosed()) {
				oConnection.close();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			//closeConection(oConnection, oStatement, oResultSet);
		}
	}
}
