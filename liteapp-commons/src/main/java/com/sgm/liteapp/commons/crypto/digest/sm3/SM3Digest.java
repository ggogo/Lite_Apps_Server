package com.sgm.liteapp.commons.crypto.digest.sm3;

import org.bouncycastle.crypto.digests.GeneralDigest;
import org.bouncycastle.crypto.util.Pack;

public class SM3Digest extends GeneralDigest implements Memoable {

	private int[] V = new int[8];

	private int[] inwords = new int[16];

	private int xOff;

	private int[] W = new int[68];

	private int[] W1 = new int[64];

	private static final int[] T = new int[64];

	static {
		for (int i = 0; i < 16; i++) {
			int t = 2043430169;
			T[i] = (t << i | t >>> 32 - i);
		}
		for (int i = 16; i < 64; i++) {
			int n = i % 32;
			int t = 2055708042;
			T[i] = (t << n | t >>> 32 - n);
		}
	}

	public SM3Digest() {
		reset();
	}

	public SM3Digest(SM3Digest t) {
		super(t);

		copyIn(t);
	}

	private void copyIn(SM3Digest t) {
		System.arraycopy(t.V, 0, this.V, 0, this.V.length);
		System.arraycopy(t.inwords, 0, this.inwords, 0, this.inwords.length);
		this.xOff = t.xOff;
	}

	public String getAlgorithmName() {
		return "SM3";
	}

	public int getDigestSize() {
		return 32;
	}

	public Memoable copy() {
		return new SM3Digest(this);
	}

	public void reset(Memoable other) {
		SM3Digest d = (SM3Digest) other;

		copyIn(d);
	}

	public void reset() {
		super.reset();

		this.V[0] = 1937774191;
		this.V[1] = 1226093241;
		this.V[2] = 388252375;
		this.V[3] = -628488704;
		this.V[4] = -1452330820;
		this.V[5] = 372324522;
		this.V[6] = -477237683;
		this.V[7] = -1325724082;

		this.xOff = 0;
	}

	public int doFinal(byte[] out, int outOff) {
		finish();

		Pack.intToBigEndian(this.V[0], out, outOff + 0);
		Pack.intToBigEndian(this.V[1], out, outOff + 4);
		Pack.intToBigEndian(this.V[2], out, outOff + 8);
		Pack.intToBigEndian(this.V[3], out, outOff + 12);
		Pack.intToBigEndian(this.V[4], out, outOff + 16);
		Pack.intToBigEndian(this.V[5], out, outOff + 20);
		Pack.intToBigEndian(this.V[6], out, outOff + 24);
		Pack.intToBigEndian(this.V[7], out, outOff + 28);

		reset();

		return 32;
	}

	protected void processWord(byte[] in, int inOff) {
		int n = (in[inOff] & 0xFF) << 24 | (in[(++inOff)] & 0xFF) << 16 | (in[(++inOff)] & 0xFF) << 8
				| in[(++inOff)] & 0xFF;

		this.inwords[this.xOff] = n;
		this.xOff += 1;
		if (this.xOff >= 16) {
			processBlock();
		}
	}

	protected void processLength(long bitLength) {
		if (this.xOff > 14) {
			this.inwords[this.xOff] = 0;
			this.xOff += 1;

			processBlock();
		}
		while (this.xOff < 14) {
			this.inwords[this.xOff] = 0;
			this.xOff += 1;
		}
		this.inwords[(this.xOff++)] = ((int) (bitLength >>> 32));
		this.inwords[(this.xOff++)] = ((int) bitLength);
	}

	private int P0(int x) {
		int r9 = x << 9 | x >>> 23;
		int r17 = x << 17 | x >>> 15;
		return x ^ r9 ^ r17;
	}

	private int P1(int x) {
		int r15 = x << 15 | x >>> 17;
		int r23 = x << 23 | x >>> 9;
		return x ^ r15 ^ r23;
	}

	private int FF0(int x, int y, int z) {
		return x ^ y ^ z;
	}

	private int FF1(int x, int y, int z) {
		return x & y | x & z | y & z;
	}

	private int GG0(int x, int y, int z) {
		return x ^ y ^ z;
	}

	private int GG1(int x, int y, int z) {
		return x & y | (x ^ 0xFFFFFFFF) & z;
	}

	protected void processBlock() {
		for (int j = 0; j < 16; j++) {
			this.W[j] = this.inwords[j];
		}
		for (int j = 16; j < 68; j++) {
			int wj3 = this.W[(j - 3)];
			int r15 = wj3 << 15 | wj3 >>> 17;
			int wj13 = this.W[(j - 13)];
			int r7 = wj13 << 7 | wj13 >>> 25;
			this.W[j] = (P1(this.W[(j - 16)] ^ this.W[(j - 9)] ^ r15) ^ r7 ^ this.W[(j - 6)]);
		}
		for (int j = 0; j < 64; j++) {
			this.W1[j] = (this.W[j] ^ this.W[(j + 4)]);
		}
		int A = this.V[0];
		int B = this.V[1];
		int C = this.V[2];
		int D = this.V[3];
		int E = this.V[4];
		int F = this.V[5];
		int G = this.V[6];
		int H = this.V[7];
		for (int j = 0; j < 16; j++) {
			int a12 = A << 12 | A >>> 20;
			int s1_ = a12 + E + T[j];
			int SS1 = s1_ << 7 | s1_ >>> 25;
			int SS2 = SS1 ^ a12;
			int TT1 = FF0(A, B, C) + D + SS2 + this.W1[j];
			int TT2 = GG0(E, F, G) + H + SS1 + this.W[j];
			D = C;
			C = B << 9 | B >>> 23;
			B = A;
			A = TT1;
			H = G;
			G = F << 19 | F >>> 13;
			F = E;
			E = P0(TT2);
		}
		for (int j = 16; j < 64; j++) {
			int a12 = A << 12 | A >>> 20;
			int s1_ = a12 + E + T[j];
			int SS1 = s1_ << 7 | s1_ >>> 25;
			int SS2 = SS1 ^ a12;
			int TT1 = FF1(A, B, C) + D + SS2 + this.W1[j];
			int TT2 = GG1(E, F, G) + H + SS1 + this.W[j];
			D = C;
			C = B << 9 | B >>> 23;
			B = A;
			A = TT1;
			H = G;
			G = F << 19 | F >>> 13;
			F = E;
			E = P0(TT2);
		}
		this.V[0] ^= A;
		this.V[1] ^= B;
		this.V[2] ^= C;
		this.V[3] ^= D;
		this.V[4] ^= E;
		this.V[5] ^= F;
		this.V[6] ^= G;
		this.V[7] ^= H;

		this.xOff = 0;
	}
}
