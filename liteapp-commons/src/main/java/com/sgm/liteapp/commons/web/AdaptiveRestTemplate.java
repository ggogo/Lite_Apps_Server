package com.sgm.liteapp.commons.web;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.nio.charset.StandardCharsets;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;
import com.sgm.liteapp.commons.cms.CustomSSLSocketFactory;

/**
 * 根据请求的URL自动选择HTTP/HTTPS连接方式
 * 
 * @author liup
 *
 */
public class AdaptiveRestTemplate extends RestTemplate {

	public void initAdaption() {
		// 修改默认的异常处理器，否则当返回非200时走不到ResponseEntity就抛异常了
		super.setErrorHandler(new DefaultResponseErrorHandler() {
			@Override
			protected void handleError(ClientHttpResponse response, HttpStatus statusCode) throws IOException {

			}
		});
		super.setRequestFactory(new AdaptiveClientRequestFactory());
		// 返回内容使用UTF-8编码，避免ResponseEntity.getBody内容乱码
		super.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
	}

	protected static final class AdaptiveClientRequestFactory extends SimpleClientHttpRequestFactory {
		@Override
		protected void prepareConnection(HttpURLConnection connection, String httpMethod) {
			try {
				if (!(connection instanceof HttpsURLConnection)) {
					super.prepareConnection(connection, httpMethod);
					return;
				}
				HttpsURLConnection httpsConnection = (HttpsURLConnection) connection;
				TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {

					@Override
					public void checkClientTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

					}

					@Override
					public void checkServerTrusted(X509Certificate[] arg0, String arg1) throws CertificateException {

					}

					@Override
					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

				} };
				SSLContext sslContext = SSLContext.getInstance("TLS");
				sslContext.init(null, trustAllCerts, new java.security.SecureRandom());
				httpsConnection.setSSLSocketFactory(new CustomSSLSocketFactory(sslContext.getSocketFactory()));
				httpsConnection.setHostnameVerifier(new HostnameVerifier() {
					@Override
					public boolean verify(String s, SSLSession sslSession) {
						return true;
					}
				});

				super.prepareConnection(httpsConnection, httpMethod);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
