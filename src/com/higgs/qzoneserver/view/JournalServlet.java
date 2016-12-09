package com.higgs.qzoneserver.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higgs.qzoneserver.biz.JournalBiz;
import com.higgs.qzoneserver.biz.impl.JournalBizImpl;
import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.model.Journal;

public class JournalServlet extends HttpServlet {

	/**
	 * Constructor of the object.
	 */
	public JournalServlet() {
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
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		
		PrintWriter out = response.getWriter();
		int action=UtiyCommon.getParseInt(request.getParameter("action"));
		out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if (action==1) {
			List<Journal> oList=new ArrayList<Journal>();
			JournalBiz oBiz=new JournalBizImpl();
			oList=oBiz.getJournaList();
			if (oList!=null) {
				out.print("<Result errcount=\"0\"  journalcount=\""+oList.size()+"\" >");
				for (int i = 0; i <oList.size(); i++) {
					out.print("<journalinfo>");
					out.print("<id>"+oList.get(i).getId()+"</id>");
					out.print("<title>"+oList.get(i).getTitle()+"</title>");
					out.print("<context>"+oList.get(i).getContext()+"</context>");
					out.print("<readcount>"+oList.get(i).getReadCount()+"</readcount>");
					out.print("<typeid>"+oList.get(i).getTypeId()+"</typeid>");
					out.print("<userid>"+oList.get(i).getUserId()+"</userid>");
					out.print("<posttime>"+oList.get(i).getPostTime()+"</posttime>");
					out.print("</journalinfo>");
				}
			}else {
				out.print("<Result errcount=\"1\" errmsg=\"异常，无返回值\">");
			}
			out.print("</Result>");
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
