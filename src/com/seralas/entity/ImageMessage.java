package com.seralas.entity;



public class ImageMessage extends BaseMessage{
	private com.seralas.entity.Image Image;
	//private UploadImage upimage;   //上传到服务器的

	public Image getImage() {
		return Image;
	}

	public void setImage(com.seralas.entity.Image image2) {
		Image = image2;
	}

	@Override
	public String toString() {
		return "ImageMessage [Image=" + Image + "]";
	}
	
	
	


}
