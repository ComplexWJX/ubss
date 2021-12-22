package com.itany.entity;

import java.math.BigDecimal;

public class File extends CloudDriver{

	private BigDecimal filesize;
	public void setFilesize(BigDecimal filesize) {
		this.filesize = filesize;
	}
	public BigDecimal getFilesize() {
		return filesize;
	}
	
	
}
