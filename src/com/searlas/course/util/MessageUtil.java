package com.searlas.course.util;


import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.seralas.entity.Image;
import com.seralas.entity.ImageMessage;
import com.seralas.entity.Music;
import com.seralas.entity.MusicMessage;
import com.seralas.entity.News;
import com.seralas.entity.NewsMessage;
import com.seralas.entity.TextMessage;
import com.seralas.entity.UploadImage;
import com.thoughtworks.xstream.XStream;
/**
 * 
 *@author Searlas
 *@return
 *@throws IOException
 *@throws DocumentException
 */
public class MessageUtil {
	public static final String req_msgtype_text = "text";
	public static final String req_msgtype_voice = "voice";
	public static final String req_msgtype_image = "image";
	public static final String req_msgtype_video = "video";
	public static final String req_msgtype_news = "news";
	public static final String req_msgtype_music = "music";
	public static final String MESSAGE_EVNET = "event";
	public static final String MESSAGE_LOCATION = "location";
	public static final String req_msgtype_location = "location";
	public static final String req_msgtype_link = "link";
	public static final String req_msgtype_subscribe = "subscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";
	
	public static Map<String,String> xmlToMap(HttpServletRequest request) throws DocumentException, IOException{
		Map<String,String> map = new HashMap<String,String>();
		SAXReader reader =new SAXReader();
		
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);
		Element root =doc.getRootElement();
		List<Element> list = root.elements();
		
		for(Element e:list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;	
	}
	/**
	 * 文字消息转xml
	 * @param textMessage
	 * @return
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return 	xstream.toXML(textMessage);
		
	}
	/**
	 * 图片消息转xml
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return 	xstream.toXML(imageMessage);
		
	}
	
	/**
	 * 图文消息转换xml
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return 	xstream.toXML(newsMessage);
		
	}
	/**
	 * 音乐消息转xml
	 * 
	 * @param musicMessage
	 * @return
	 */
	public static String musicMessageToXml(MusicMessage musicMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", musicMessage.getClass());
		return 	xstream.toXML(musicMessage);
		
	}
	/**
	 * 图文消息的组装
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initNewsMessage(String toUserName,String fromUserName){
		String message = null;
		List<News> newsList =new ArrayList<News>();
		NewsMessage newsMessage = new NewsMessage();
		News news =new  News();
		news.setTitle("震惊！微信公众号这样开发");
		news.setDescription("this is the description");
		//news.setPicUrl("searlas.ngrok.cc/Wechat/img/455.jpg");
		news.setPicUrl("http://img.mukewang.com/576b7ad60001263e06000338.jpg");
		news.setUrl("www.sina.com.cn");
		newsList.add(news);

		
		
		news = new News();
		news.setTitle("摔跤吧！爸爸");
		news.setDescription("Dangal");
		//news.setPicUrl("searlas.ngrok.cc/Wechat/img/455.jpg");
		news.setPicUrl("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2457983084.webp");
		news.setUrl("www.dysfz.cc/key/"+news.getDescription());
		newsList.add(news);
		
		
		
		news = new News();
		news.setTitle("记忆大师");
		news.setDescription("记忆大师");
		//news.setPicUrl("searlas.ngrok.cc/Wechat/img/455.jpg");
		news.setPicUrl("https://img3.doubanio.com/view/movie_poster_cover/lpst/public/p2455156816.webp");
		news.setUrl("www.dysfz.cc/key/"+news.getDescription());
		newsList.add(news);

		
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(req_msgtype_news);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message= newsMessageToXml(newsMessage);
		return message;
		
	}
	/**组装图片消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @param ImageId
	 * @return
	 */
	public static String initImageMessage(String toUserName,String fromUserName){
		String message =null;
		Image image = new Image();
		image.setMediaId("0lyzB09SrjvEtnyhQkeyzAOAV-6od4Y-y1lYJKoGagG7xhoEikUgSkMUwsHSfR6U");
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setMsgType(req_msgtype_image);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		message = imageMessageToXml(imageMessage);
		
		return message;
	}
	
	
	/**
	 * 组装音乐消息
	 * 
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 */
	public static String initMusicMessage(String toUserName,String fromUserName){
		String message =null;
		Music music = new Music();
		music.setThumbMediaId ("LQbuaaSqKvjKeGvs5fNIJ5ESMgiZfaNDMiZG0DK1M-4OZw-dDRjWb5lTt2hxYl32");
		music.setTitle("烟火里的尘埃");
		music.setDescription("songed by hcy");
		music.setMusicUrl("http://m8.music.126.net/20170519135128/2d1848f3ab40768f3956a5850b84c132/ymusic/T7xwL_606Hpobvp4pYz55A==/509951162777803296");
		music.setHQMusicUrl("http://m8.music.126.net/20170519135128/2d1848f3ab40768f3956a5850b84c132/ymusic/T7xwL_606Hpobvp4pYz55A==/509951162777803296");
		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setFromUserName(toUserName);
		musicMessage.setMsgType(req_msgtype_music);
		musicMessage.setToUserName(fromUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMusic(music);
		message = musicMessageToXml(musicMessage);
		
		return message;
	}
}




