package com.searlas.course.service;

import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.searlas.course.util.MessageUtil;
import com.seralas.entity.NewsMessage;
import com.seralas.entity.TextMessage;

/**
 * 核心服务类
 * @author Searlas
 * 
 *
 */

public class CoreService {
	//默认返回的内容
	public static String respContent ="未知的消息类型！";                                                                                         
	public static String introduceContent="这是物联网杀虫公司的微信公众号";    
	public static String Device_Info="设备列表\n device1 ok \n device2 ok";                                                                        
	public static String menuContent ="MENU\n\n发送1 获取介绍\n\n发送2 获取图片\n\n发送3 获取音乐\n\n发送4  获取新消息\n\n发送？获取帮助";                                
	
	
	public static String processRequest(HttpServletRequest req){
		
		                                                                          
		//回复文本消息
		String message = null;
		try{
			Map<String,String> map =MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			System.out.println(map);
			
			TextMessage text = new TextMessage();
			text.setFromUserName(toUserName);
			text.setToUserName(fromUserName);
			text.setMsgType("text");
			text.setCreateTime(new Date().getTime());
			if(msgType.equals(MessageUtil.req_msgtype_text)){    //发送文字消息
				String content = map.get("Content");
				if("1".equals(content)){
					text.setContent(introduceContent);
					message=MessageUtil.textMessageToXml(text);
				}else if("2".equals(content)){
					message=MessageUtil.initImageMessage(toUserName, fromUserName);
				}else if("3".equals(content)){
					message=MessageUtil.initMusicMessage(toUserName, fromUserName);
				}else if("?".equals(content)||"？".equals(content)){
					text.setContent("联系微信官方获取帮助");
					message=MessageUtil.textMessageToXml(text);
				}else if("4".equals(content)){
					message=MessageUtil.initNewsMessage(toUserName, fromUserName);
				}else{
					text.setContent("您发送的消息是："+content+menuContent);
					message=MessageUtil.textMessageToXml(text);
				}
				return message;
			}else if(msgType.equals(MessageUtil.req_msgtype_image)){
				String content = "图片";
				text.setContent("您发送的是"+content+menuContent);
				message=MessageUtil.textMessageToXml(text);	
				return message;
			}else if(msgType.equals(MessageUtil.req_msgtype_voice)){
				String content = "声音";
				text.setContent("您发送的是"+content+menuContent);
				message=MessageUtil.textMessageToXml(text);
				return message;
			}else if(msgType.equals(MessageUtil.req_msgtype_video)){
				String content = "视频";
				text.setContent("您发送的是"+content+menuContent);
				message=MessageUtil.textMessageToXml(text);
				return message;
			}else if(MessageUtil.MESSAGE_EVNET.equals(msgType)){                   //用户发送事件消息
				String event =map.get("Event");
				if(event.equals(MessageUtil.MESSAGE_CLICK)){      				   //用户点击菜单事件
					String eventkey = map.get("EventKey");
					message = React(eventkey, text);
					
				}else if(event.equals(MessageUtil.MESSAGE_VIEW)){
					String url = map.get("EventKey");
					text.setContent(url);
					message=MessageUtil.textMessageToXml(text);
				}else if(event.equals(MessageUtil.MESSAGE_SCANCODE)){
					String key = map.get("EventKey");
					text.setContent(key);
					message=MessageUtil.textMessageToXml(text);
				}else if(event.equals(MessageUtil.req_msgtype_subscribe)){
					String content = "欢迎订阅！";
					text.setContent(content+menuContent);
					message=MessageUtil.textMessageToXml(text);
				}
				return message;
			}else if(MessageUtil.MESSAGE_LOCATION.equals(msgType)){                //用户发送位置信息
				String Label = map.get("Label");
				text.setContent("您发送的位置:\n"+Label);
				message=MessageUtil.textMessageToXml(text);
				return message;
			}
			
		}catch(Exception e){
			e.printStackTrace();
			return respContent;
		}
		return message;
	}
	public static String React(String EventKey,TextMessage text){
		String message= null;
		if(EventKey.equals("12")){
			text.setContent(Device_Info);
			message=MessageUtil.textMessageToXml(text);
		}else if(EventKey.equals("11")){
			text.setContent(menuContent);
			message=MessageUtil.textMessageToXml(text);
		}
		return message;
	}
}
