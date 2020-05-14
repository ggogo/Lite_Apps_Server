package com.sgm.liteapp.admin.manager.impl;

import org.springframework.stereotype.Component;
import com.sgm.liteapp.admin.manager.LiteappReleaseManager;

@Component
public class LiteappReleaseManagerImpl implements LiteappReleaseManager {

	@Override
	public byte[] createDiffPackageZipFile(byte[] from, byte[] to) {
		throw new UnsupportedOperationException("当前还不支持差分包");
	}
}
