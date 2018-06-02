package com.searlas.course.test;

import java.io.IOException;

import com.searlas.api.trans.TransResult;
import com.searlas.course.service.ModelHandle;
import com.searlas.course.servlet.Test;
import com.searlas.course.util.MenuUtil;
import com.searlas.course.util.ModelUtil;
import com.searlas.course.util.WechatUtil;
import com.searlas.model.entity.WechatTemplate;
import com.seralas.entity.AccessToken;

import net.sf.json.JSONObject;


public class WechatTest {
	public static void main(String[] args) {
		
		deletemenu();
		createmenu();
		//sendmodel();发送模版消息
		
	
	}
	
	private static void sendmodel() {
		WechatTemplate temp = new WechatTemplate();
		AccessToken token = WechatUtil.getAccessToken();
		temp = ModelHandle.initTemplate(WechatUtil.getOpenid());
		int result = ModelUtil.sendModel(token.getToken(), temp);
		System.out.println("result"+result);
	}

	public static void gettoken(){
		AccessToken token = WechatUtil.getAccessToken();
		String path  = Thread.currentThread().getContextClassLoader ().getResource("").getPath();
	
		System.out.println(WechatUtil.getPath());
		System.out.println("票据"+token.getToken());
		System.out.println("有效时间"+token.getExpiresIn());
	}
	public static void uploadpic(){
		AccessToken token = WechatUtil.getLocalAccessToken();
		String pathpic = "D:/this.jpg";
		try {
			String mediaId = WechatUtil.upload(pathpic, token.getToken(), "image");
			System.out.println(mediaId);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static void uploadthumb(){
		AccessToken token = WechatUtil.getAccessToken();
		String pathpic = "D:/6.jpg";
		try{
			String mediaId = WechatUtil.upload(pathpic, token.getToken(), "thumb");
			System.out.println(mediaId);
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public static void createmenu(){
		AccessToken token = WechatUtil.getAccessToken();
		String menu = JSONObject.fromObject(MenuUtil.initMenu()).toString();
		int result = MenuUtil.createMenu(token.getToken(), menu);
		if(result == 0 ){
			System.out.println("创建菜单成功");
		}else{
			System.out.println("错误码"+result);
		}
	}
	public static void querymenu(){
		AccessToken token = WechatUtil.getAccessToken();
		JSONObject jsonObject =MenuUtil.queryMenu(token.getToken());
		System.out.println(jsonObject);
	}
	public static void deletemenu(){
		AccessToken token = WechatUtil.getAccessToken();
		int result =MenuUtil.deleteMenu(token.getToken());
		if(result==0)
			System.out.println("删除成功");
		else 
			System.out.println("错误码"+result);
	}
	public static void trans(){
		TransResult tr = new TransResult();
		tr = WechatUtil.trans();
		System.out.println(tr.toString());
	}
}
