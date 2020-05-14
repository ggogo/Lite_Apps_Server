package com.sgm.liteapp.commons.crypto.digest.sm3;

public abstract interface Memoable {

	public abstract Memoable copy();

	public abstract void reset(Memoable paramMemoable);

}
