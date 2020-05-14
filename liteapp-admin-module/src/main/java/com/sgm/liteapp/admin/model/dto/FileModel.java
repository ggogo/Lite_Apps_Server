package com.sgm.liteapp.admin.model.dto;

import java.io.InputStream;
import java.io.Serializable;

public class FileModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5577708572316625846L;
	/**
	 * 解压后文件的名字
	 */
	String fileName;
	/**
	 * 文件类型
	 */
	String fileType;
	/**
	 * 解压后每个文件的输入流
	 */
	InputStream fileInputstream;
	
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFileType() {
		return fileType;
	}
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}
	public InputStream getFileInputstream() {
		return fileInputstream;
	}
	public void setFileInputstream(InputStream fileInputstream) {
		this.fileInputstream = fileInputstream;
	}
	@Override
	public String toString() {
		return "FileModel [fileName=" + fileName + ", fileType=" + fileType + ", fileInputstream=" + fileInputstream
				+ "]";
	}

	
}
