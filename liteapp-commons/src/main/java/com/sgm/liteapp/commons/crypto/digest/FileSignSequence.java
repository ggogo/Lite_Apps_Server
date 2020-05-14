package com.sgm.liteapp.commons.crypto.digest;

import org.bouncycastle.asn1.ASN1EncodableVector;
import org.bouncycastle.asn1.ASN1Integer;
import org.bouncycastle.asn1.ASN1ObjectIdentifier;
import org.bouncycastle.asn1.DEROctetString;
import org.bouncycastle.asn1.DERSequence;

public class FileSignSequence {

	private ASN1Integer version;

	private DEROctetString data;

	private ASN1ObjectIdentifier digestAlgorithmIdentifier;

	private DEROctetString signature;

	public FileSignSequence(Integer version, byte[] data, String identifier, byte[] signature) {
		this.version = new ASN1Integer(version.intValue());
		this.data = new DEROctetString(data);
		this.digestAlgorithmIdentifier = new ASN1ObjectIdentifier(identifier);
		this.signature = new DEROctetString(signature);
	}

	public DERSequence getDERSeqObject() {
		ASN1EncodableVector v = new ASN1EncodableVector();
		v.add(this.version);
		v.add(this.digestAlgorithmIdentifier);
		v.add(this.signature);
		v.add(this.data);
		DERSequence seq = new DERSequence(v);
		return seq;
	}
}
