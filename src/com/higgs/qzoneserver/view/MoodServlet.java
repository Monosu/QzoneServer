package com.higgs.qzoneserver.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higgs.qzoneserver.biz.MoodBiz;
import com.higgs.qzoneserver.biz.impl.MoodBizImpl;
import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.model.Mood;

public class MoodServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public MoodServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		int action = UtiyCommon.getParseInt(request.getParameter("action"));
		out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		MoodBiz oMoodBiz  = new MoodBizImpl();
		if (action==1) {
			Mood oMood = new Mood();
			oMood.setFid(request.getParameter("fid"));
			//oMood.setMoodContext(request.getParameter("mc"));
			String moodContext = request.getParameter("mc");
			oMood.setMoodContext(new String(moodContext.getBytes("ISO-8859-1"),"UTF-8"));
			oMood.setPostTime(new Date());
			oMood.setUserId(UtiyCommon.getParseInt(request.getParameter("uid")) );
			if (oMood.getUserId()>0&&!UtiyCommon.isEmpty(oMood.getMoodContext()) ) {
				if (oMoodBiz.addMood(oMood)) {
					out.print("<Result errcount=\"0\" ></Result>");
				}else {
					out.print("<Result errcount=\"3\" errmsg=\"发表失败\"></Result>");
				}
				
			}else {
				out.print("<Result errcount=\"1\" errmsg=\"发表用户或内容不能为空\" ></Result>");
			}
		}else {
			out.print("<Result errcount=\"2\" errmsg=\"非法参数\" ></Result>");
		}
		
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
