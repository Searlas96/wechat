package com.searlas.course.util;


import com.searlas.model.entity.WechatTemplate;

import net.sf.json.JSONObject;


/** 
 * 创建菜单 
 *  
 * @param menu 菜单实例 
 * @param accessToken 有效的access_token 
 * @return 0表示成功，其他值表示失败 
 */  
public class ModelUtil {
	public static final String SEND_TEMPLATE_URL ="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	public static int sendModel(String token,WechatTemplate wt){
		int  result = 0;
		
		String jsonString = JSONObject.fromObject(wt).toString().replace("day", "Day");
		System.out.println("jsonstring"+jsonString);
		String requestUrl = SEND_TEMPLATE_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WechatUtil.httpRequest(requestUrl, "POST", jsonString);
		System.out.println(jsonObject);
		
		if(jsonObject != null){
			if(jsonObject.getInt("errcode") != 0){
				result = jsonObject.getInt("errcode");
				System.out.println("错误码："+result+jsonObject.getString("errmsg"));
			}
			else{
				System.out.println(jsonObject);
			}
		}
		return result;
	}
	
}
