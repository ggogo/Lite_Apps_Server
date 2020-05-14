package com.sgm.liteapp.commons.utils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;
import org.apache.commons.lang.StringUtils;

public class ZipUtils {

	/**
	 * 解压出压缩包中的某个文件，并以字节数组读出
	 * 
	 * @param is
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static byte[] searchFile(InputStream is, String name) throws IOException {
		ZipInputStream zin = null;
		try {
			zin = new ZipInputStream(is);
			ZipEntry zipEntry;
			BufferedReader br = null;
			while ((zipEntry = zin.getNextEntry()) != null) {
				if (zipEntry.isDirectory()) {

				} else {
					String entryName = zipEntry.getName();
					if (StringUtils.equals(entryName, name) || StringUtils.endsWith(entryName, "/" + name)) {
						br = new BufferedReader(new InputStreamReader(zin, "UTF-8"));
						StringBuffer sb = new StringBuffer();
						String line = br.readLine();
						while (line != null) {
							sb.append(line).append("\r\n");
							line = br.readLine();
						}
						return sb.toString().getBytes("UTF-8");
					}
				}
			}
			return null;
		} catch (IOException e) {
			throw e;
		} finally {
			if (zin != null) {
				zin.close();
			}
		}

	}

	/**
	 * 解压出压缩包中的某个文件，并以文本方式读出
	 * 
	 * @param is
	 * @param name
	 * @return
	 * @throws IOException
	 */
	@SuppressWarnings("resource")
	public static String searchFileText(InputStream is, String name) throws IOException {
		ZipInputStream zin = null;
		try {
			zin = new ZipInputStream(is);
			ZipEntry zipEntry;
			BufferedReader br = null;
			while ((zipEntry = zin.getNextEntry()) != null) {
				if (zipEntry.isDirectory()) {

				} else {
					String entryName = zipEntry.getName();
					if (StringUtils.equals(entryName, name) || StringUtils.endsWith(entryName, "/" + name)) {
						br = new BufferedReader(new InputStreamReader(zin, "UTF-8"));
						StringBuffer sb = new StringBuffer();
						String line = br.readLine();
						while (line != null) {
							sb.append(line).append("\r\n");
							line = br.readLine();
						}
						return sb.toString();
					}
				}
			}
			return null;
		} catch (IOException e) {
			throw e;
		} finally {
			if (zin != null) {
				zin.close();
			}
		}
	}

	/**
	 * 将多个文件压缩为一个文件
	 * 
	 * @param fileList
	 * @return
	 * @throws IOException
	 */
	public static byte[] compress(Map<String, byte[]> fileList) throws IOException {
		if (fileList == null || fileList.size() < 1) {
			throw new IOException("无法压缩空文件");
		}
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		ZipOutputStream zos = new ZipOutputStream(baos);
		try {
			for (Map.Entry<String, byte[]> entry : fileList.entrySet()) {
				zos.putNextEntry(new ZipEntry(entry.getKey()));
				zos.write(entry.getValue());
				zos.closeEntry();
			}
			zos.close();
			return baos.toByteArray();
		} catch (IOException e) {
			throw e;
		} finally {
			if (zos != null) {
				zos.close();
			}
			if (baos != null) {
				baos.close();
			}
		}
	}

}
