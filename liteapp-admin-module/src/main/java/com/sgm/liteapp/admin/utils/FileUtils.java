package com.sgm.liteapp.admin.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import org.springframework.util.Assert;
import org.springframework.web.multipart.MultipartFile;

import com.sgm.liteapp.admin.exception.ResultException;
import com.sgm.liteapp.admin.model.dto.FileModel;

public class FileUtils {

	/**
	 * 对zip类型的文件进行解压
	 */
	public static List<FileModel> unzip(MultipartFile file) {
		
		List<FileModel> fileModelList = new ArrayList<FileModel>();
		String zipFileName = null;
		// 对文件进行解析
		try {
			ZipInputStream zipInputStream = new ZipInputStream(file.getInputStream(), Charset.forName("GBK"));
			BufferedInputStream bs = new BufferedInputStream(zipInputStream);
			ZipEntry zipEntry;
			byte[] bytes = null;
			while ((zipEntry = zipInputStream.getNextEntry()) != null) { // 获取zip包中的每一个zip file entry
				zipFileName = zipEntry.getName();
				Assert.notNull(zipFileName, "压缩文件中子文件的名字格式不正确");
				FileModel fileModel = new FileModel();
				fileModel.setFileName(zipFileName);
				bytes = new byte[(int) zipEntry.getSize()];
				bs.read(bytes, 0, (int) zipEntry.getSize());
				InputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
				fileModel.setFileInputstream(byteArrayInputStream);
				fileModelList.add(fileModel);
			}
		} catch (Exception e) {
			new ResultException("读取上传包内容失败,请确认包格式是否正确:" + zipFileName);
		}
		return fileModelList;
	}
}
