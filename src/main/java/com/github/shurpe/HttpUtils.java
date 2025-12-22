package com.github.shurpe;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ssl.AllowAllHostnameVerifier;
import org.apache.http.conn.ssl.SSLContextBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;

public final class a {

    public static String a(final String b) throws Exception {
        try (final CloseableHttpClient c = b()) {
            final HttpGet d = new HttpGet(b);
            d.setHeader("Accept", "application/json");
            final HttpResponse e = c.execute(d);

            return EntityUtils.toString(e.getEntity());
        }
    }

    // fix for javax.net.ssl.SSLHandshakeException
    private static CloseableHttpClient b() throws Exception {
        final SSLContext f = new SSLContextBuilder()
                .loadTrustMaterial(null, (x509CertChain, authType) -> true)
                .build();

        return HttpClientBuilder.create()
                .setSslcontext(f)
                .disableContentCompression()
                .setHostnameVerifier(new AllowAllHostnameVerifier())
                .build();
    }
}
