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

import com.searlas.course.service.CoreService;
import com.searlas.course.util.MessageUtil;
import com.searlas.course.util.SignUtil;
import com.seralas.entity.ImageMessage;
import com.seralas.entity.TextMessage;
@WebServlet("/Servlet")

//searlas.ngrok.cc
public class TestServlet extends HttpServlet{
	/*
	 * 请求处理的核心类
	 * 
	 * @author searlas
	 * 
	 * */
	
	private static final long serialVersionUID =111111L;
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
		req.setCharacterEncoding("UTF-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		String message = CoreService.processRequest(req);
		System.out.println(message);
		out.println(message);
		out.close();
		out = null;
		/*try {
			Map<String,String> map =MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			System.out.println(map);
			String message =null;
			if("text".equals(msgType)){
				String content = map.get("Content");
				TextMessage text = new TextMessage();
				text.setFromUserName(toUserName);
				text.setToUserName(fromUserName);
				text.setMsgType("text");
				text.setCreateTime(new Date().getTime());
				text.setContent("您发送的消息是："+content);
				message=MessageUtil.textMessageToXml(text);
			}
			else if("iamge".equals(msgType)){
				ImageMessage image = new ImageMessage();
				String picUrl = map.get("PicUrl");
				image.setImage();
			}
			else if("voice".equals(msgType)){
				
			}
			out.println(message);
			System.out.println(message);
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}*/
	}
	
}