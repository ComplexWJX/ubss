package com.itany.opp;

import java.math.BigDecimal;

import com.itany.entity.CloudDriver;
import com.itany.entity.File;

public class Main {

	public static void main(String[] args) {
		CloudDriver cloudDriver = new CloudDriver();
		cloudDriver.setFullsize(new BigDecimal(5000000));
		cloudDriver.setImgsize(new BigDecimal(2000000));
		File file = new File();
		file.setFilesize(cloudDriver.getFullsize().add(cloudDriver.getImgsize()));
		BigDecimal filesize = file.getFilesize();
		System.out.println(filesize);
	}
}
