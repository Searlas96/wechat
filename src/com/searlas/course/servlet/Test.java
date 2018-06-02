package com.searlas.course.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import com.searlas.course.util.MessageUtil;
import com.searlas.course.util.SignUtil;
import com.seralas.entity.TextMessage;

/**
 * Servlet implementation class Test
 */
@WebServlet("/Test")
public class Test extends HttpServlet {
       
	private static final long serialVersionUID =1L;
	//请求校验（确认请求来自微信服务器）
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//微信加密签名
		String signature = req.getParameter("signature");
		//时间戳
		String timestamp = req.getParameter("timestamp");
		//随机数
		String nonce = req.getParameter("nonce");
		//随机字符串
		String echostr =req.getParameter("echostr");
		
		
		
		PrintWriter out =resp.getWriter();
		//请求检验 若成功则返回echostr 表示接入成功 否则接入失败
		if(SignUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);
		}
		out.close();
		out = null;
	}
	//处理微信服务器发来的消息
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
	}

}
