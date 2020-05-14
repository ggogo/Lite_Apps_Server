package com.sgm.liteapp.commons.cms;

public interface CMSService {

	/**
	 * 上传文件到CMS素材库(共有文件)
	 * 
	 * @param fileName
	 * @param file
	 * @param mimeType
	 * @return
	 */
	public Material uploadMaterial(String fileName, byte[] file) throws CMSServiceException;

	/**
	 * 上传文件到CMS素材库(可以选择私有文件)
	 * 
	 * @param fileName
	 * @param file
	 * @param secured
	 * @return
	 * @throws CMSServiceException
	 */
	public Material uploadMaterial(String fileName, byte[] file, boolean secured) throws CMSServiceException;

	/**
	 * 当文件为私有文件时，必须使用token才能下载，该方法返回特定文件ID的拼接token后的URL，可以一次传多个文件
	 * 
	 * @param fileObjKey
	 * @return
	 */
	public SecuredFileURLs getSecuredFileURLs(String... fileObjKey) throws CMSServiceException;

}
