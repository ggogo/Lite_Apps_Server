package com.sgm.liteapp.commons.cms;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

public class CustomSSLSocketFactory extends SSLSocketFactory {

	private final SSLSocketFactory delegate;

	public CustomSSLSocketFactory(SSLSocketFactory delegate) {
		this.delegate = delegate;
	}

	@Override
	public String[] getDefaultCipherSuites() {
		return delegate.getDefaultCipherSuites();
	}

	@Override
	public String[] getSupportedCipherSuites() {
		return delegate.getSupportedCipherSuites();
	}

	@Override
	public Socket createSocket(final Socket socket, final String host, final int port, final boolean autoClose)
			throws IOException {
		final Socket underlyingSocket = delegate.createSocket(socket, host, port, autoClose);
		return overrideProtocol(underlyingSocket);
	}

	@Override
	public Socket createSocket(final String host, final int port) throws IOException {
		final Socket underlyingSocket = delegate.createSocket(host, port);
		return overrideProtocol(underlyingSocket);
	}

	@Override
	public Socket createSocket(final String host, final int port, final InetAddress localAddress, final int localPort)
			throws IOException {
		final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
		return overrideProtocol(underlyingSocket);
	}

	@Override
	public Socket createSocket(final InetAddress host, final int port) throws IOException {
		final Socket underlyingSocket = delegate.createSocket(host, port);
		return overrideProtocol(underlyingSocket);
	}

	@Override
	public Socket createSocket(final InetAddress host, final int port, final InetAddress localAddress,
			final int localPort) throws IOException {
		final Socket underlyingSocket = delegate.createSocket(host, port, localAddress, localPort);
		return overrideProtocol(underlyingSocket);
	}

	private Socket overrideProtocol(final Socket socket) {
		if (!(socket instanceof SSLSocket)) {
			throw new RuntimeException("An instance of SSLSocket is expected");
		}
		((SSLSocket) socket).setEnabledProtocols(new String[] { "TLSv1" });
		return socket;
	}
}
