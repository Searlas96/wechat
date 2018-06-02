package com.searlas.course.util;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import com.searlas.api.trans.TransResult;
import com.seralas.entity.AccessToken;
import com.seralas.menu.entity.*;
import com.searlas.course.util.MyX509TrustManager;

import net.sf.json.JSONObject;

public class WechatUtil {
	
	private static final String OPENID = "oPkCzwqVzq2aHPaBk3W0u-_BmEHY" ;
	private static final String APPID = "wx40b5a069d69a1180" ;
	private static final String APPSECRET ="67101b0bb3f4bce6bdc68baeacdc5893";
	private static final String ACCESS_TOKEN_URL ="https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	private static final String UPLOAD_URL="https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";

	private static final String TRANS_URL="http://api.fanyi.baidu.com/api/trans/vip/translate?q=apple&from=en&to=zh&appid=2015063000000001&salt=1435660288&sign=f89f9594663708c1605f3d736d01d2d4";
	private static final String TIP_URL="https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=ACCESS_TOKEN";
	private static final String TRANSATION_TEMPLATE_ID="tZRcKNudJaYcc-vvNdrK-QgY5Esnr3-lgrpR-6lpt9Q";
	
	private static String path  =  Thread.currentThread().getContextClassLoader ().getResource("").getPath();

	public static String getPath() {
		return path;
	}
	
	
	  /** 
     * 发起https请求并获取结果 
     *  
     * @param requestUrl 请求地址 
     * @param requestMethod 请求方式（GET、POST） 
     * @param outputStr 提交的数据 
     * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值) 
     */  
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {  
        JSONObject jsonObject = null;  
        StringBuffer buffer = new StringBuffer();  
        try {  
        	// 创建SSLContext对象，并使用我们指定的信任管理器初始化  
            TrustManager[] tm = { new MyX509TrustManager() };  
            SSLContext sslContext = SSLContext.getInstance("SSL", "SunJSSE");  
            sslContext.init(null, tm, new java.security.SecureRandom());  
            // 从上述SSLContext对象中得到SSLSocketFactory对象  
            SSLSocketFactory ssf = sslContext.getSocketFactory();  
  
            URL url = new URL(requestUrl);  
            HttpsURLConnection httpUrlConn = (HttpsURLConnection) url.openConnection();  
            httpUrlConn.setSSLSocketFactory(ssf);  
  
            httpUrlConn.setDoOutput(true);  
            httpUrlConn.setDoInput(true);  
            httpUrlConn.setUseCaches(false);  
            // 设置请求方式（GET/POST）  
            httpUrlConn.setRequestMethod(requestMethod);  
  
            if ("GET".equalsIgnoreCase(requestMethod))  
                httpUrlConn.connect();  
            // 当有数据需要提交时  
            if (null != outputStr) {  
                OutputStream outputStream = httpUrlConn.getOutputStream();  
             // 注意编码格式，防止中文乱码  
                outputStream.write(outputStr.getBytes("UTF-8"));  
                outputStream.close();  
            }  
            // 将返回的输入流转换成字符串  
            InputStream inputStream = httpUrlConn.getInputStream();  
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, "utf-8");  
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);  
  
            String str = null;  
            while ((str = bufferedReader.readLine()) != null) {  
                buffer.append(str);  
            }  
            bufferedReader.close();  
            inputStreamReader.close();  
            // 释放资源  
            inputStream.close();  
            inputStream = null;  
            httpUrlConn.disconnect();  
            jsonObject = JSONObject.fromObject(buffer.toString());  
        } catch (ConnectException ce) {  
            System.out.println("Weixin server connection timed out.");  
        } catch (Exception e) {  
            System.out.println("https request error:"+ e);  
        }  
        return jsonObject;  
    }  
	
	public static JSONObject doGetStr(String url){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject =null;
		try {
			HttpResponse response = httpClient.execute(httpGet);
			HttpEntity entity =  response.getEntity();
			if(entity != null){
				String result = EntityUtils.toString(entity);
				jsonObject = jsonObject.fromObject(result);
			}
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	public static JSONObject doPostStr(String url,String outStr){
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpPost httpPost = new HttpPost(url);
		JSONObject jsonObject = null;
		try {
			httpPost.setEntity(new StringEntity(outStr,"UTF-8"));
			HttpResponse response = httpClient.execute(httpPost);
			String result =EntityUtils.toString(response.getEntity(),"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return jsonObject;
	}
	
	
	
	/**
	 * 
	 * 获取access_token
	 * 
	 * @return
	 */
	public static AccessToken getAccessToken(){
		Properties p = new Properties();
		AccessToken token = new AccessToken(); 
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null)
		{
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		try {
			p.load(new FileInputStream(getPath()+"/access_token.properties"));
			System.out.println("temp---"+p.get("access_token"));
			p.setProperty("access_token",token.getToken());
			p.store(new FileOutputStream(getPath()+"/access_token.properties"), "author:searlas");
			System.out.println("refresh---"+p.get("access_token"));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return token;
	}
	/**
	 * 
	 * 获取本地access_token
	 * 
	 * @return
	 */
	public static AccessToken getLocalAccessToken(){
		Properties p = new Properties();
		AccessToken token = new AccessToken(); 
		try {
			p.load(new FileInputStream(getPath()+"/access_token.properties"));
			System.out.println(p.get("access_token"));
			token.setToken(p.getProperty("access_token"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return token;
	}
	
	/**
	 * 上传临时素材
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 * @throws IOException
	 */
	public static String upload(String filePath,String accessToken,String type) throws IOException{
		File file = new File(filePath);
		if(!file.exists() || !file.isFile()){
			throw new IOException("读取文件错误");
		}
		
		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		URL urlObj=new URL(url);
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST");
		con.setDoInput(true);
		con.setDoOutput(true); 
		con.setUseCaches(false);
		
		//设置请求头信息
		con.setRequestProperty("Connection","Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		
		//设置边界
		String BOUNDARY = "--------"+System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data);boundary="+BOUNDARY);
		
		StringBuffer sb = new StringBuffer();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition:form-data;name=\"file\";filename=\""+file.getName()+"\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		
		byte[] head = sb.toString().getBytes("utf-8");
		
		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);
		
		//文件正文部分
		//把文件以流文件的方式推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes=0;
		byte[] bufferOut = new byte[1024];
		while((bytes=in.read(bufferOut)) != -1){
			out.write(bufferOut,0,bytes);
		}
		in.close();
		
		//结尾部分
		byte[] foot =("\r\n--" + BOUNDARY +"--\r\n").getBytes("utf-8");//定义最后数据分隔线
		
		out.write(foot);
		out.flush();
		out.close();
		
		StringBuffer buffer =new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try{
			//定义bufferreader输入流来读取URL的响应
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line =null;
			while((line = reader.readLine()) != null){
				buffer.append(line);
			}
			if(result == null){
				result = buffer.toString();
			}
			
		}catch(IOException e){
			e.printStackTrace();
		}finally{
			if(reader != null){
				reader.close();
			}
		}
		
		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if(!"image".equals(type)){
			typeName = type +"_media_id";
		}
		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}
	
	
	public static TransResult trans(){
		TransResult tr = new TransResult();
		JSONObject json = doGetStr(TRANS_URL);
		System.out.println(json);
		tr = (TransResult) JSONObject.toBean(json,TransResult.class);
		
		return tr;
	}
	
	public static String CheckDevice (){
		String DeviceInfo = null;
		
		
		return DeviceInfo;
	}
	
	public static String TransationTip(){
		
		return null;
	}


	public static String getOpenid() {
		return OPENID;
	}


	public static String getTipUrl() {
		return TIP_URL;
	}


	public static String getTransationTemplateId() {
		return TRANSATION_TEMPLATE_ID;
	}
}




