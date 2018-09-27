/**
 * 
 */
package mx.ine.acuerdos.seguridad;

import java.io.IOException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.conn.ssl.X509HostnameVerifier;
import org.apache.http.impl.client.DefaultHttpClient;

/**
 * @author INE
 *
 */
public class SSLUtilidades {

	public static HttpClient wrapClient(HttpClient base) {
	    try {
	        SSLContext ctx = SSLContext.getInstance("TLSv1.2");
	        X509TrustManager tm = new X509TrustManager() {
	           

				@Override
				public void checkClientTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				@Override
				public void checkServerTrusted(X509Certificate[] arg0,
						String arg1) throws CertificateException {
					// TODO Auto-generated method stub
					
				}
				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}
	        };
	        X509HostnameVerifier verifier = new X509HostnameVerifier() {
//	            public void verify(String string, SSLSocket ssls) throws IOException {
//	            }
//
//	            public void verify(String string, X509Certificate xc) throws SSLException {
//	            }
//
//	            public void verify(String string, String[] strings, String[] strings1) throws SSLException {
//	            }
//
//	            public boolean verify(String string, SSLSession ssls) {
//	                return true;
//	            }

				@Override
				public boolean verify(String arg0, SSLSession arg1) {
					// TODO Auto-generated method stub
					return false;
				}

				@Override
				public void verify(String arg0, SSLSocket arg1)
						throws IOException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void verify(String arg0, X509Certificate arg1)
						throws SSLException {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void verify(String arg0, String[] arg1, String[] arg2)
						throws SSLException {
					// TODO Auto-generated method stub
					
				}
	        };
	        ctx.init(null, new TrustManager[]{tm}, null);
	        SSLSocketFactory ssf = new SSLSocketFactory(ctx);
	        ssf.setHostnameVerifier(verifier);
	        ClientConnectionManager ccm = base.getConnectionManager();
	        SchemeRegistry sr = ccm.getSchemeRegistry();
	        sr.register(new Scheme("https", ssf, 443));
	        return new DefaultHttpClient(ccm, base.getParams());
	    } catch (Exception ex) {
	        ex.printStackTrace();
	        return null;
	    }
	}
	
}
