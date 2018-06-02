package com.seralas.menu.entity;

public class MiniprogramButton extends Button {
		private String key;
		private String url;
		private String appid;
		private String pagepath;
		public String getKey() {
			return key;
		}
		public void setKey(String key) {
			this.key = key;
		}
		public String getUrl() {
			return url;
		}
		public void setUrl(String url) {
			this.url = url;
		}
		public String getAppid() {
			return appid;
		}
		public void setAppid(String appid) {
			this.appid = appid;
		}
		public String getPagepath() {
			return pagepath;
		}
		public void setPagepath(String pagepath) {
			this.pagepath = pagepath;
		}
		@Override
		public String toString() {
			return "MiniprogramButton [key=" + key + ", url=" + url + ", appid=" + appid + ", pagepath=" + pagepath
					+ "]";
		}


}
