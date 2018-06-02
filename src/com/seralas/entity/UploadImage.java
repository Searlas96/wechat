package com.seralas.entity;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

public class UploadImage extends Image {
	private String ImageId;
	@Override
	public Graphics getGraphics() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getHeight(ImageObserver arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Object getProperty(String arg0, ImageObserver arg1) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ImageProducer getSource() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getWidth(ImageObserver arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	public String getImageId() {
		return ImageId;
	}

	public void setImageId(String imageId) {
		ImageId = imageId;
	}
	
}
