package com.higgs.qzoneserver.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.higgs.qzoneserver.biz.UserInfoBiz;
import com.higgs.qzoneserver.biz.impl.UserInfoBizImpl;
import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.model.UserInfo;

public class UserServer extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public UserServer() {
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
		response.setCharacterEncoding("UTF-8");
		request.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		int action = UtiyCommon.getParseInt(request.getParameter("action")) ;
		//用户登陆
		Object[] parm = new Object[2];
		UserInfoBiz oBiz = new UserInfoBizImpl();
		if (action==1) {
			UserInfo oInfo = new UserInfo();
//			parm[0]=3;
//			parm[1] = "用户名或密码为空";
			oInfo.setLoginName(request.getParameter("username"));
			oInfo.setPassWord(request.getParameter("password"));
			
			UserInfo userInfo = oBiz.userLogin(oInfo, parm);
			
			out.print("<Result errcount=\""+parm[0]+"\" errmsg=\""+parm[1]+"\">");
			if (userInfo!=null) {
				outUser(userInfo, out);
			}
			out.print("</Result>");
		}else if (action==2){
			UserInfo oInfo = new UserInfo();
//			parm[0]=7;
//			parm[1] = "用户名或密码为空";
			oInfo.setLoginName(request.getParameter("username"));
			oInfo.setPassWord(request.getParameter("password"));
			UserInfo userInfo = null;
			if (UtiyCommon.isEmpty(oInfo.getLoginName())) {
				parm[0]=7;
				parm[1]= "用户名不能为空";
			}else if (UtiyCommon.isEmpty(oInfo.getPassWord())) {
				parm[0]=8;
				parm[1]= "密码不能为空";
			}else {
				oInfo.setLastTime(new Date());
				oInfo.setRegisterTime(new Date());
				oInfo.setSex("男");
				oInfo.setNikeName(oInfo.getLoginName());
				oInfo.setUserHeadImg(0);
			    userInfo = oBiz.userRegister(oInfo, parm);
			}
			
			out.print("<Result errcount=\""+parm[0]+"\" errmsg=\""+parm[1]+"\">");
			if (userInfo!=null) {
				outUser(userInfo, out);
			}
			out.print("</Result>");
		}else if (action==3) {
			//获取到搜索关键字
			String selectString = request.getParameter("select");
			selectString = new String(selectString.getBytes("ISO8859-1"),"UTF-8");
			//获取到当前要加载的页数	
			int page =UtiyCommon.getParseInt(request.getParameter("page"),1) ;
			//获取到当前要加载的条数
			int pagesize = UtiyCommon.getParseInt(request.getParameter("pagesize"),20);
			//根据参数查询数据库
			List<UserInfo> dataList = oBiz.getUserInfos(selectString, page, pagesize);
			//如果参数不为空并且有值。循环输出xml
			if (dataList!=null&&dataList.size()>0) {
				out.print("<Result count=\""+dataList.size()+"\">");
				for (UserInfo userInfo : dataList) {
					out.print("<userinfo>");
					out.print("<id>"+userInfo.getId()+"</id>");
					out.print("<loginname>"+userInfo.getLoginName()+"</loginname>");
					out.print("<lasttime>"+userInfo.getLastTime()+"</lasttime>");
					out.print("<registertime>"+userInfo.getRegisterTime()+"</registertime>");
					out.print("<sex>"+userInfo.getSex()+"</sex>");
					out.print("<nikename>"+userInfo.getNikeName()+"</nikename>");
					out.print("</userinfo>");
				}
				out.print("</Result>");
			}else {
				//否则输出count=0。代表没有取到值
				out.print("<Result count=\"0\">");
				out.print("</Result>");
			}
		}else if (action==4) {
			parm[0]=4;
			parm[1]="异常错误";
			UserInfo oInfo=new UserInfo();
			oInfo.setLoginName(request.getParameter("username"));
			oInfo.setNikeName(request.getParameter("nikename"));
			oInfo.setPassWord(request.getParameter("userpsd"));
			String seultString=oBiz.updateUser(oInfo, parm);
			out.print("<Result errcount=\""+parm[0]+"\" errmsg=\""+parm[1]+"\">");
			if (seultString==null||seultString.equals("")) {
				out.print("<info>修改密码成功</info>");
			}
			out.print("</Result>");
		}
		out.flush();
		out.close();
	}
	private void outUser(UserInfo userInfo,PrintWriter out) {
		out.print("<userinfo>");
		out.print("<id>"+userInfo.getId()+"</id>");
		out.print("<loginname>"+userInfo.getLoginName()+"</loginname>");
		out.print("<password>"+userInfo.getPassWord()+"</password>");
		out.print("<lasttime>"+userInfo.getLastTime()+"</lasttime>");
		out.print("<registertime>"+userInfo.getRegisterTime()+"</registertime>");
		out.print("<sex>"+userInfo.getSex()+"</sex>");
		out.print("<nikename>"+userInfo.getNikeName()+"</nikename>");
		out.print("</userinfo>");
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
