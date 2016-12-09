package com.higgs.qzoneserver.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.higgs.qzoneserver.biz.UserFilesBiz;
import com.higgs.qzoneserver.biz.impl.UserFilesBizImpl;
import com.higgs.qzoneserver.common.UtiyCommon;
import com.higgs.qzoneserver.model.UserFiles;
import com.jspsmart.upload.File;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

public class UserFilesServlet extends HttpServlet {
	/**
	 * Constructor of the object.
	 */
	public UserFilesServlet() {
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
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		int id = UtiyCommon.getParseInt(request.getParameter("action")) ;
		UserFilesBiz oFilesBiz = new UserFilesBizImpl();
		Object[] parm = new Object[2];
		out.print("<?xml version=\"1.0\" encoding=\"UTF-8\"?>");
		if (id==1) {
			String uploadPath = getInitParameter("uploadpath");
			int uploadsize = UtiyCommon.getParseInt(getInitParameter("uploadsize"));
			String uploadType = getInitParameter("uploadtype");
			String noUploadType = getInitParameter("nouploadtype");
			SmartUpload oSmartUpload = new SmartUpload();
			
			try {
				oSmartUpload.initialize(getServletConfig(), request, response);
				oSmartUpload.setCharset("UTF-8");
				oSmartUpload.setTotalMaxFileSize(uploadsize);
				oSmartUpload.setAllowedFilesList(uploadType);
				oSmartUpload.setDeniedFilesList(noUploadType);
				oSmartUpload.upload();
				if (oSmartUpload.getFiles()!=null&&oSmartUpload.getFiles().getCount()>0) {
					for (int i = 0; i < oSmartUpload.getFiles().getCount(); i++) {
						File oFile = oSmartUpload.getFiles().getFile(i);
						if (!oFile.isMissing()) {
							String filePath = uploadPath+UtiyCommon.getDateFileName()+"."+oFile.getFileExt();
							oFile.saveAs(filePath);
							UserFiles oUserFiles = new UserFiles();
							oUserFiles.setFileExp(oFile.getFileExt());
							oUserFiles.setFileName(oFile.getFileName());
							oUserFiles.setFileSize(oFile.getSize());
							oUserFiles.setUploadTime(new Date());
							oUserFiles.setFilePath(filePath);
							UserFiles resultFiles = oFilesBiz.addUserFiles(oUserFiles, parm);
							out.print("<Result errcount=\""+parm[0]+"\" errmsg=\""+parm[1]+"\">");
							if (resultFiles!=null&&UtiyCommon.getParseInt(parm[0])==0) {
								out.print("<userfiles>");
								out.print("<id>"+resultFiles.getId()+"</id>");
								out.print("<fileexp>"+resultFiles.getFileExp()+"</fileexp>");
								out.print("<filename>"+resultFiles.getFileName()+"</filename>");
								out.print("<filepath>"+resultFiles.getFilePath()+"</filepath>");
								out.print("<filesize>"+resultFiles.getFileSize()+"</filesize>");
								out.print("<uploadtime>"+resultFiles.getUploadTime()+"</uploadtime>");
								out.print("</userfiles>");
								
							}
							out.print("</Result>");
						}
					}
				}else {
					out.print("<Result errcount=\"2\" errmsg=\"没有包含上传文件\"></Result>");
				}
			} catch (Exception e) {
				out.print("<Result errcount=\"3\" errmsg=\"文件上传失败\"></Result>");
				// TODO Auto-generated catch block
				//e.printStackTrace();
			}
		}
		else if (id==2) {
			List<UserFiles> oList=oFilesBiz.getFilespath();
			String path = request.getContextPath();
			String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
			if (oList!=null&&oList.size()>0) {
				out.print("<Result>");
				for (int i = 0; i < oList.size(); i++) {
					out.print("<userfiles>");
					out.print("<filepath>"+basePath+oList.get(i).getFilePath()+"</filepath>");;
					out.print("</userfiles>");
				}
				out.print("</Result>");
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
