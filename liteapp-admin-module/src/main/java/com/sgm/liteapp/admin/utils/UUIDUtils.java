package com.sgm.liteapp.admin.utils;

import java.util.UUID;

public class UUIDUtils {

	/**
	 * UUID生成
	 * @return
	 */
	public static String getUUID(){
		String uuid=UUID.randomUUID().toString();
		return uuid.replace("-", "");
	}
	
}
