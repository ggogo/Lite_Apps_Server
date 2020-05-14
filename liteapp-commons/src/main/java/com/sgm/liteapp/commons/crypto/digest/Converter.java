package com.sgm.liteapp.commons.crypto.digest;

public class Converter {

	public static byte[] intToBytes(int num) {
		byte[] bytes = new byte[4];
		bytes[0] = ((byte) (0xFF & num >> 0));
		bytes[1] = ((byte) (0xFF & num >> 8));
		bytes[2] = ((byte) (0xFF & num >> 16));
		bytes[3] = ((byte) (0xFF & num >> 24));
		return bytes;
	}

	public static int byteToInt(byte[] bytes) {
		int num = 0;

		int temp = (0xFF & bytes[0]) << 0;
		num |= temp;
		temp = (0xFF & bytes[1]) << 8;
		num |= temp;
		temp = (0xFF & bytes[2]) << 16;
		num |= temp;
		temp = (0xFF & bytes[3]) << 24;
		num |= temp;
		return num;
	}

	public static byte[] longToBytes(long num) {
		byte[] bytes = new byte[8];
		for (int i = 0; i < 8; i++) {
			bytes[i] = ((byte) (int) (0xFF & num >> i * 8));
		}
		return bytes;
	}

	public static String bytesToHexString(byte[] src) {
		StringBuffer sb = new StringBuffer("");
		if ((src == null) || (src.length <= 0)) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				sb.append(0);
			}
			sb.append(hv);
		}
		return sb.toString();
	}

	public static byte[] hexStringToBytes(String hexString) {
		if ((hexString == null) || (hexString.equals(""))) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = ((byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[(pos + 1)])));
		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	public static byte[] IntToByte(int num) {
		byte[] bytes = new byte[4];

		bytes[0] = ((byte) (0xFF & num >> 0));
		bytes[1] = ((byte) (0xFF & num >> 8));
		bytes[2] = ((byte) (0xFF & num >> 16));
		bytes[3] = ((byte) (0xFF & num >> 24));

		return bytes;
	}

}
