package com.searlas.course.util;

import com.seralas.menu.entity.Button;
import com.seralas.menu.entity.ClickButton;
import com.seralas.menu.entity.Menu;
import com.seralas.menu.entity.MiniprogramButton;
import com.seralas.menu.entity.ViewButton;

import net.sf.json.JSONObject;

public class MenuUtil {

	private static final String CREATE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	private static final String QUERY_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	private static final String DELETE_MENU_URL="https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	/**
	 * 组装菜单
	 * 
	 * @return
	 */
	public static Menu initMenu(){
		Menu menu =  new Menu();
		ClickButton button11 = new ClickButton();
		button11.setName("显示menu");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button12 = new ViewButton();
		button12.setName("团购");
		button12.setType("view");
		button12.setUrl("http://www.nbutminter.com/buy_buy_buy_2017-06-13/wechat/ActiIndex.jsp");
/*		ViewButton button12 = new ViewButton();
		button12.setName("显示设备信息");
		button12.setType("view");
		button12.setUrl("http://searlas.ngrok.cc/PestKiller/wechat/device-info");
*/		
		ViewButton button13 = new ViewButton();
		button13.setName("设备维修历史");
		button13.setType("view");
		button13.setUrl("http://searlas.ngrok.cc/PestKiller/wechat/repair-record");
		
	/*	ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://www.xiazaiba.com");*/
		
		ClickButton button21 = new ClickButton();
		button21.setName("扫码事件");
		button21.setType("scancode_push");
		button21.setKey("21");
		
		ViewButton button22 = new ViewButton();
		button22.setName("土特产列表");
		button22.setType("view");
		button22.setUrl("http://www.nbutminter.com/buy_buy_buy_2017-06-13/wechat/ActiIndex.jsp");
		
		/*ClickButton button31 = new ClickButton();
		button31.setName("地理位置");
		button31.setType("location_select");
		button31.setKey("31");*/
		ViewButton button31= new ViewButton();
		button31.setName("绑定账号");
		button31.setType("view");
		button31.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid=wx40b5a069d69a1180&"
				+ "redirect_uri=http%3a%2f%2fwww.nbutminter.com%2fUserCodeServlet&"
				+ "response_type=code&scope=snsapi_base&"
				+ "state=123#wechat_redirect");
		
		MiniprogramButton button32 = new MiniprogramButton();
		button32.setType("miniprogram");
		button32.setName("testnimiprogram");
		button32.setAppid("wx8857999db59a191e");
		button32.setKey("32");
		button32.setPagepath("/index");
		///不支持小程序的客户端将打开url
		button32.setUrl("url");
		
		ViewButton button33= new ViewButton();
		button33.setName("个人订单");
		button33.setType("view");
		button33.setUrl("https://www.nbutminster.com/wechat/my.jsp");
		
		
		//main
		Button button1 = new Button();
		button1.setName("团购");
		button1.setSub_button(new Button[]{button12});
		
		Button button2 = new Button();
		button2.setName("2号主菜单");
		button2.setSub_button(new Button[]{button21,button22});

		Button button3 = new Button();
		button3.setName("我的信息");
		button3.setSub_button(new Button[]{button31});
		
	
		
		//menu
		menu.setButton(new Button[]{button1,button3});

		System.out.println("menu"+menu.toString());
		
		return menu;
		
	}
	
	public static int createMenu(String token,String menu){
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", token);
		System.out.println(menu);
		JSONObject jsonObject = WechatUtil.doPostStr(url, menu);
		if(jsonObject != null)
		{
			result = jsonObject.getInt("errcode");
			
		}
		return result;
	}
	
	public static JSONObject queryMenu(String token){
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject jsonObject = WechatUtil.doGetStr(url);
		return jsonObject; 
	}
	public static int deleteMenu(String token){
		int result = 0;
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", token);
		JSONObject json = WechatUtil.doGetStr(url);
		if(json != null){
			result=json.getInt("errcode");
		}
		return result; 
	}
}
