package com.hsh.ftp;

import java.io.File;
import java.io.IOException;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.Thumbnails.Builder;

public class PicResizeTask implements Runnable {
	
	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private String directory;
	
	private String fileName;


	@Override
	public void run() {
		String newFile = directory  +  fileName.substring(0,fileName.lastIndexOf("."));
		// 同比缩放
		try {
			Builder<File> b = Thumbnails
			.of(directory  + fileName);
			b.size(72 , 72)
			.outputFormat("jpg")
			.outputQuality(1.0f)
			.toFile(newFile+"_72.jpg");
			
			b = Thumbnails
					.of(directory  + fileName);
			b.size(126 , 126)
			.outputFormat("jpg")
			.outputQuality(1.0f)
			.toFile(newFile+"_126.jpg");
			
			b = Thumbnails
			.of(directory  + fileName);
			b.size(640 ,200)
			.outputFormat("jpg")
			.outputQuality(1.0f)
			.toFile(newFile+"_640.jpg");
		} catch (IOException e) {
			e.printStackTrace();
		}
				        
	}
	
	public String getDirectory() {
		return directory;
	}

	public void setDirectory(String directory) {
		this.directory = directory;
	}


}
