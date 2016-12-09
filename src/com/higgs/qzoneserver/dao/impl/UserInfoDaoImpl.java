package com.higgs.qzoneserver.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;


import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.dao.UserInfoDao;
import com.higgs.qzoneserver.model.UserInfo;

public class UserInfoDaoImpl implements UserInfoDao{
	private Connection oConnection;
	private PreparedStatement oStatement;
	private ResultSet oResultSet;
	CallableStatement oCallableStatement = null;
	/**
	 * 用户登陆
	 */
	public UserInfo userLogin(UserInfo oInfo, Object[] outParm) {
		// TODO Auto-generated method stub
		UserInfo userInfo = null;
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			//定义执行存储过程的语句。参数用?号代替
			String sName = "call UserLogin(?,?,?,?,?)";
			try {
				//实例化执行存储过程的对象。为CallableStatement不是PreparedStatement。这2个都是Statement
				//接口下的实现类。用来替代Statement接口执行JDBC操作
				oCallableStatement =oConnection.prepareCall(sName);
				//给存储过程in参数赋值。在存储过程中有3种类型参数。1.in 2.out 3.inout 
				//in类型和PreparedStatement赋值一样。直接setXXX即可。out参数需要
				//手动注册,inout 就先赋值在注册。2则写法2合1.公用一个下标。比如第一个参数为inout类型
				//则可以设置oCallableStatement.setString(1, oInfo.getLoginName());
				//先设置in 参数。然后在写oCallableStatement.registerOutParameter(1, Types.VARCHAR, 200);
				//注册out类型
				oCallableStatement.setString(1, oInfo.getLoginName());
				oCallableStatement.setString(2, oInfo.getPassWord());
				//注册out类型参数。第一个参数为参数的位数，第二个为参数类型。第三个为参数的长度
				oCallableStatement.registerOutParameter(3, Types.INTEGER);
				oCallableStatement.registerOutParameter(4, Types.VARCHAR, 200);
				oCallableStatement.registerOutParameter(5, Types.INTEGER);
				//执行储存过程。因为存储过程内部可以包含多个SQL语句。所以与PreparedStatement不同的是
				//这里的executeQuery和executeUpdate()都会执行存储过程。但是executeUpdate()
				//会返回执行后受影响的行数。而executeQuery返回执行后产生查询的结果集
				oResultSet=oCallableStatement.executeQuery();
				//执行后获取存储过程中的out参数的值。通过oCallableStatement.registerOutParameter.getXXX()
				//方法获取。传入的参数为存储过程里面out变量名称
				outParm[0] = oCallableStatement.getInt("errcount");
				outParm[1] = oCallableStatement.getString("errMsg");
				int userid = oCallableStatement.getInt("userid");
				if (Integer.parseInt(String.valueOf(outParm[0]))==0) {
					userInfo = getUserInfoById(userid);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oCallableStatement, oResultSet);
			}
			
		}
		return userInfo;
	}
	private UserInfo readDbToUserInfo(ResultSet oSet){
		UserInfo userInfo = null;
		if (oSet!=null) {
			userInfo = new UserInfo();
			try {
				userInfo.setId(oSet.getInt("id"));
				userInfo.setLoginName(oSet.getString("LoginName"));
				userInfo.setPassWord(oSet.getString("passWord"));
				userInfo.setLastTime(UtiyCommon.stringParseDate(oSet.getString("lastTime")));
				userInfo.setRegisterTime(UtiyCommon.stringParseDate(oSet.getString("registerTime")));
				userInfo.setNikeName(oSet.getString("nikeName"));
				userInfo.setSex(oSet.getString("sex"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return userInfo;
		
	}
	public UserInfo getUserInfoById(int id) {
		// TODO Auto-generated method stub
		UserInfo oUserInfo = null;
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			String sql = "select * from userinfo where id=?";
			try {
				oStatement = oConnection.prepareStatement(sql);
				oStatement.setInt(1, id);
				oResultSet = oStatement.executeQuery();
				if (oResultSet.next()) {
					oUserInfo = readDbToUserInfo(oResultSet);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
			
		}
		return oUserInfo;
	}
	public UserInfo userRegister(UserInfo oInfo, Object[] outParm) {
		// TODO Auto-generated method stub
		UserInfo userInfo = null;
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			//定义执行存储过程的语句。参数用?号代替
			String sName = "call userRegister(?,?,?,?,?,?,?,?,?,?)";
			CallableStatement oCallableStatement = null;
			try {
				//实例化执行存储过程的对象。为CallableStatement不是PreparedStatement。这2个都是Statement
				//接口下的实现类。用来替代Statement接口执行JDBC操作
				oCallableStatement =oConnection.prepareCall(sName);
				//给存储过程in参数赋值。在存储过程中有3种类型参数。1.in 2.out 3.inout 
				//in类型和PreparedStatement赋值一样。直接setXXX即可。out参数需要
				//手动注册,inout 就先赋值在注册。2则写法2合1.公用一个下标。比如第一个参数为inout类型
				//则可以设置oCallableStatement.setString(1, oInfo.getLoginName());
				//先设置in 参数。然后在写oCallableStatement.registerOutParameter(1, Types.VARCHAR, 200);
				//注册out类型
				oCallableStatement.setString(1, oInfo.getLoginName());
				oCallableStatement.setString(2, oInfo.getPassWord());
				oCallableStatement.setString(3, UtiyCommon.dataFormt(oInfo.getLastTime()));
				oCallableStatement.setString(4, UtiyCommon.dataFormt( oInfo.getRegisterTime()));
				oCallableStatement.setString(5, oInfo.getSex());
				oCallableStatement.setString(6, oInfo.getNikeName());
				oCallableStatement.setInt(7, oInfo.getUserHeadImg());
				//注册out类型参数。第一个参数为参数的位数，第二个为参数类型。第三个为参数的长度
				oCallableStatement.registerOutParameter(8, Types.INTEGER);
				oCallableStatement.registerOutParameter(9, Types.VARCHAR, 200);
				oCallableStatement.registerOutParameter(10, Types.INTEGER);
				//执行储存过程。因为存储过程内部可以包含多个SQL语句。所以与PreparedStatement不同的是
				//这里的executeQuery和executeUpdate()都会执行存储过程。但是executeUpdate()
				//会返回执行后受影响的行数。而executeQuery返回执行后产生查询的结果集
				oResultSet=oCallableStatement.executeQuery();
				//执行后获取存储过程中的out参数的值。通过oCallableStatement.registerOutParameter.getXXX()
				//方法获取。传入的参数为存储过程里面out变量名称
				outParm[0] = oCallableStatement.getInt("errcount");
				outParm[1] = oCallableStatement.getString("errMsg");
				int userid = oCallableStatement.getInt("userid");
				if (Integer.parseInt(String.valueOf(outParm[0]))==0) {
					userInfo = getUserInfoById(userid);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oCallableStatement, oResultSet);
			}
			
		}
		return userInfo;

	}
	/**
	 * 获取用户信息
	 * parm selectString 要搜索的关键字
	 * page 要查询的页码
	 * pagesize 每页取多少条信息
	 */
	public List<UserInfo> getUserInfos(String selectString, int page,
			int pagesize) {
		List<UserInfo> dateList = null;
		//获取数据库连接
		oConnection = DBHelper.getConnection();
		if (oConnection!=null) {
			//定义sql语句。如果查询条件不为空。则设置模糊查询条件
			String sqlString = "select * from userinfo";
			if (!UtiyCommon.isEmpty(selectString)) {
				sqlString +=" where nikeName like ?";
			}
			//定义limit取数据的范围
			sqlString += " order by id asc limit ?,?";
			try {
				//计算limit从第几条数据开始取
				int selectCount = page>1?(page-1)*pagesize : 0;
				oStatement = oConnection.prepareStatement(sqlString);
				//赋值参数。如果有带查询条件则加上模糊查询昵称的条件
				if (!UtiyCommon.isEmpty(selectString)) {
					oStatement.setString(1, "%"+selectString+"%");
					oStatement.setInt(2, selectCount);
					oStatement.setInt(3, pagesize);
				}else {
					oStatement.setInt(1, selectCount);
					oStatement.setInt(2, pagesize);
				}
				//执行查询
				oResultSet = oStatement.executeQuery();
				//如果有返回数据集。循环读取到list内并返回
				if (oResultSet!=null) {
					dateList = new ArrayList<UserInfo>();
					while (oResultSet.next()) {
						UserInfo oInfo = readDbToUserInfo(oResultSet);
						if (oInfo!=null) {
							dateList.add(oInfo);
						}
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
			
		}
		// TODO Auto-generated method stub
		return dateList;
	}
	public String updateUser(UserInfo oUserInfo, Object[] parm) {
		String prosql="call find_password(?,?,?,?,?)";
		String result="";
		oConnection=DBHelper.getConnection();
		if (oConnection!=null) {
			try {
				oCallableStatement=oConnection.prepareCall(prosql);
				oCallableStatement.setString(1, oUserInfo.getLoginName());
				oCallableStatement.setString(2, oUserInfo.getNikeName());
				oCallableStatement.setString(3, oUserInfo.getPassWord());
				oCallableStatement.registerOutParameter(4, Types.INTEGER);
				oCallableStatement.registerOutParameter(5, Types.VARCHAR, 20);
				oResultSet=oCallableStatement.executeQuery();
				if (oResultSet!=null) {
					parm[0]=oCallableStatement.getInt("errcount");
					parm[1]=oCallableStatement.getString("errmsg");
					result=(String) parm[1];
				}		
			} catch (SQLException e) {
				e.printStackTrace();
			}finally{
				DBHelper.closeConection(oConnection, oStatement, oResultSet);
			}
		}
		return result;
		
	}

}
