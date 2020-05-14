package com.sgm.liteapp.commons.csapi;

public interface CSAPIService {

	public String getAccessToken() throws CSAPIException;

	public String uploadFile(String fileName, byte[] fileContent) throws CSAPIException;

	public FileEntity getFile(String fileId) throws CSAPIException;

	public void deleteFile(String fileId) throws CSAPIException;
}
