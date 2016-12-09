package com.higgs.qzoneserver.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higgs.qzoneserver.biz.UserFriendBiz;
import com.higgs.qzoneserver.biz.impl.UserFriendBizImpl;
import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.model.UserFriend;

public class UserFriendServer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserFriendServer() {
		super();
	}

	/**
	 * Destruction of the servlet. <br>
	 */
	public void destroy() {
		super.destroy(); // Just puts "destroy" string in log
		// Put your code here
	}

	/**
	 * The doGet method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to get.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doPost(request, response);
	}

	/**
	 * The doPost method of the servlet. <br>
	 *
	 * This method is called when a form has its tag value method equals to post.
	 * 
	 * @param request the request send by the client to the server
	 * @param response the response send by the server to the client
	 * @throws ServletException if an error occurred
	 * @throws IOException if an error occurred
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		//接收请求类别
		int action = UtiyCommon.getParseInt(request.getParameter("action"));
		out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		UserFriendBiz oBiz = new UserFriendBizImpl();
		
		if (action==1) {
			int userid = UtiyCommon.getParseInt(request.getParameter("uid"));
			int friendid = UtiyCommon.getParseInt(request.getParameter("fid"));
			String remarkName = request.getParameter("rname");
			UserFriend oFriend = new UserFriend();
			oFriend.setUserId(userid);
			oFriend.setFriendId(friendid);
			oFriend.setRemarkName(remarkName);
			
			if(oBiz.addUserFriend(oFriend)){
				out.print("<Result errcount=\"0\"></Result>");
			}else{
				out.print("<Result errcount=\"1\" errmsg=\"添加好友失败\"></Result>");
			}
		}else if (action==2) {
			int id = UtiyCommon.getParseInt(request.getParameter("fid"));
			int userid = UtiyCommon.getParseInt(request.getParameter("uid"));
			if (id<=0||userid<=0) {
				out.print("<Result errcount=\"1\" errmsg=\"非法参数\"></Result>");
			}else {
				UserFriend oFriend = new UserFriend();
				oFriend.setFriendId(id);
				oFriend.setUserId(userid);
				if (oBiz.deleteFriend(oFriend)) {
					out.print("<Result errcount=\"0\"></Result>");
				}else {
					out.print("<Result errcount=\"2\" errmsg=\"删除好友失败\"></Result>");
				}
			}
		}else if (action==3) {
			int userid = UtiyCommon.getParseInt(request.getParameter("uid"));
			if (userid<=0) {
				out.print("<Result errcount=\"1\" errmsg=\"非法参数\"></Result>");
			}else {
				List<UserFriend> dataList = oBiz.getUserFriends(userid);
				if (dataList!=null&&dataList.size()>0) {
					out.print("<Result usercount=\""+dataList.size()+"\">");
					for (UserFriend userFriend : dataList) {
						out.print("<userfriend>");
						out.print("<id>"+userFriend.getId()+"</id>");
						out.print("<friendid>"+userFriend.getFriendId()+"</friendid>");
						out.print("<userid>"+userFriend.getUserId()+"</userid>");
						out.print("<remarname>"+userFriend.getRemarkName()+"</remarname>");
						out.print("</userfriend>");
					}
					out.print("</Result>");
				}else {
					out.print("<Result usercount=\"0\"></Result>");
				}
			}
		}else {
			out.print("<Result errcount=\"1\" errmsg=\"非法请求\"></Result>");
		}
		
		
		out.flush();
		out.close();
	}

	/**
	 * Initialization of the servlet. <br>
	 *
	 * @throws ServletException if an error occurs
	 */
	public void init() throws ServletException {
		// Put your code here
	}

}
