package techown.shanghu.https;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class SSLClient {

	/**
	 * ��ʼ��SSL�����ģ�ʹ���ܽ��������κλ���������֤�顣
	 * @throws KeyManagementException
	 * @throws NoSuchAlgorithmException
	 */
	public static void initializeSSLContext() throws KeyManagementException,
			NoSuchAlgorithmException {
		// ��ȡSSL������
		SSLContext context = SSLContext.getInstance("SSL");
		// ��֤�����³�ʼ��
		context.init(null, new TrustManager[] { new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain,
					String authType) {
			}

			public void checkServerTrusted(X509Certificate[] chain,
					String authType) {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return new X509Certificate[] {};
			}
		} }, new java.security.SecureRandom());
		// ����SSL�׽��ֹ���
		HttpsURLConnection.setDefaultSSLSocketFactory(context
				.getSocketFactory());
		// ������������֤����
		HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
			public boolean verify(String hostname, SSLSession session) {
				return true;
			}
		});
	}
	
}
