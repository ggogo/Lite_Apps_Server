package com.sgm.liteapp.admin.manager;

public interface LiteappReleaseManager {

	public byte[] createDiffPackageZipFile(byte[] from, byte[] to);
}
