package com.sgm.liteapp.admin.manager;

/**
 * SGM PKI提供的数字签名服务
 * 
 * @author liup
 *
 */
public interface PKISignatureManager {

	/**
	 * 文件签名，改变源文件内容(默认算法)
	 * 
	 * @param srcData
	 * @return
	 * @throws Exception
	 */
	public byte[] signFile(byte[] srcData) throws Exception;

	/**
	 * 文件签名，改变源文件内容(指定算法)
	 * 
	 * @param srcData
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public byte[] signFile(byte[] srcData, String algorithm) throws Exception;

	/**
	 * 对文件摘要进行签名，返回签名字符串(指定算法)
	 * 
	 * @param srcData
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public String getDigestSignature(byte[] srcData, String algorithm) throws Exception;

	/**
	 * 对文件摘要进行签名，返回签名字符串(默认算法)
	 * 
	 * @param srcData
	 * @param algorithm
	 * @return
	 * @throws Exception
	 */
	public String getDigestSignature(byte[] srcData) throws Exception;

	/**
	 * 全内容签名，返回签名字符串
	 * 
	 * @param srcData
	 * @return
	 * @throws Exception
	 */
	public String getSignature(byte[] srcData) throws Exception;

}
