package com.searlas.course.service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import com.searlas.course.util.ModelUtil;
import com.searlas.course.util.WechatUtil;
import com.searlas.model.entity.*;

public class ModelHandle {

	public static String model_id = "tZRcKNudJaYcc-vvNdrK-QgY5Esnr3-lgrpR-6lpt9Q";
	public static String color ="#173177";
	public static String color_black = "#000000";
	public static String color_red = "#f00";
	
	/**
	 * 组装模板
	 * @param openId
	 * @return
	 */
	
    public static WechatTemplate initTemplate(String openId) {

    	/**{{first.DATA}}
    	消费时间：{{keyword1.DATA}}
    	消费单号：{{keyword2.DATA}}
    	消费金额：{{keyword3.DATA}}
    	{{remark.DATA}}*/
    	
        WechatTemplate temp = new WechatTemplate();
       
        
        TemplateData td = new TemplateData();
        FirstData fd = new FirstData();
        RemarkData rd = new RemarkData();
        TemplateData_time t1 = new TemplateData_time();
        TemplateData_num t2 = new TemplateData_num();
        TemplateData_amount t3 = new TemplateData_amount();
        
        temp.setTouser(openId);
        temp.setTemplate_id(model_id);
        temp.setUrl("http://weixin.qq.com/download");
        temp.setTopColor(color);
        
        fd.setValue("消费提醒");
        fd.setColor(color_black);
        rd.setValue("谢谢惠顾");
        rd.setColor(color_black);
        Date date=new Date();
        DateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time=format.format(date);
     	System.out.println(time);
        t1.setValue(time);
        t1.setColor(color);
        t2.setValue("001");
        t2.setColor(color_black);
        t3.setValue("99.0");
        t3.setColor(color_red);
        
        td.setFirst(fd);
        td.setKeyword1(t1);
        td.setKeyword2(t2);
        td.setKeyword3(t3);
        td.setRemark(rd);
        
        temp.setData(td);
        System.out.println("temp"+temp.toString());
        
        return temp;
    }
    
}
