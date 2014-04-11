package com.allstarxi.app.utility;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.UnknownHostException;
import java.security.KeyManagementException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.UnrecoverableKeyException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

/**
 * Created by Hesam on 26/03/14
 */
public class ContentDownloader {

    private static final String TAG = "ContentDownloader";

    /**
     * Get server response of assigned URL with HttpGet request.
     * @param url address
     * @return server response.
     */
    public static String getServerResponseByHttpGet(String url) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);
            Log.d(TAG, "Try to open => " + url);

            HttpResponse httpResponse = client.execute(get);
            int connectionStatusCode = httpResponse.getStatusLine().getStatusCode();
            Log.i(TAG, "Connection code: " + connectionStatusCode + " for request: " + url);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.d(TAG, "Server response for request " + url + " => " + serverResponse);

            if(!isStatusOk(connectionStatusCode))
                return null;

            return serverResponse;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get server response of assigned URL with HttpGet request.
     * @param url address
     * @param token Facebook token
     * @return server response.
     */
    public String getServerResponseByHttpGet(String url, String token) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpGet get = new HttpGet(url);

            get.setHeader("Content-Type", "application/json");
            get.setHeader("Authorization", "Token 1234567890ABCDEF");
            Log.d(TAG, "Try to open => " + url);

            HttpResponse httpResponse = client.execute(get);
            int connectionStatusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "Connection code: " + connectionStatusCode + " for request: " + url);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.d(TAG, "Server response for request " + url + " => " + serverResponse);

            if(!isStatusOk(connectionStatusCode))
                return null;

            return serverResponse;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get server response of assigned url through HttpPost request
     * @param url address
     * @param token Facebook token
     * @return server response
     */
    public String getServerResponseByHttpPost(String url, String token) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("token", token);
            Log.d(TAG, "Try to open => " + url);

            List<NameValuePair> pairs = new ArrayList<NameValuePair>();
            pairs.add(new BasicNameValuePair("token", token));
            post.setEntity(new UrlEncodedFormEntity(pairs));

            HttpResponse httpResponse = client.execute(post);
            int connectionStatusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "Connection code: " + connectionStatusCode + " for request: " + url);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.d(TAG, "Server response for request " + url + " => " + serverResponse);

            if(!isStatusOk(connectionStatusCode))
                return null;

            return serverResponse;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get server response of assigned url through HttpPost request
     * @param url address
     * @param token Facebook token
     * @param pairs list of NameValuePair objects
     * @return server response
     */
    public String getServerResponseByHttpPost(String url, String token, List<NameValuePair> pairs) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPost post = new HttpPost(url);
            post.setHeader("token", token);
            Log.d(TAG, "Try to open => " + url);

            post.setEntity(new UrlEncodedFormEntity(pairs));

            HttpResponse httpResponse = client.execute(post);
            int connectionStatusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "Connection code: " + connectionStatusCode + " for request: " + url);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.d(TAG, "Server response for request " + url + " => " + serverResponse);

            if(!isStatusOk(connectionStatusCode))
                return null;

            return serverResponse;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get server response of assigned url through HttpPut request
     * @param url address
     * @param token Facebook token
     * @param pairs list of NameValuePair
     * @return server response
     */
    public String getServerResponseByHttpPut(String url, String token, List<NameValuePair> pairs) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpPut put = new HttpPut(url);
            put.setHeader("token", token);
            Log.d(TAG, "Try to open => " + url);

            put.setEntity(new UrlEncodedFormEntity(pairs));

            HttpResponse httpResponse = client.execute(put);
            int connectionStatusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "Connection code: " + connectionStatusCode);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.d(TAG, "Server response for request " + url + " => " + serverResponse);

            if(!isStatusOk(connectionStatusCode))
                return null;

            return serverResponse;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Get server response of assigned url through HttpDelete request
     * @param url address
     * @param token Facebook token
     * @return server response
     */
    public String getServerResponseByHttpDelete(String url, String token) {

        try {
            HttpClient client = new DefaultHttpClient();
            HttpDelete delete = new HttpDelete(url);
            delete.setHeader("token", token);
            Log.d(TAG, "Try to open => " + url);

            HttpResponse httpResponse = client.execute(delete);
            int connectionStatusCode = httpResponse.getStatusLine().getStatusCode();
            Log.d(TAG, "Connection code: " + connectionStatusCode + " for request: " + url);

            HttpEntity entity = httpResponse.getEntity();
            String serverResponse = EntityUtils.toString(entity);
            Log.d(TAG, "Server response for request " + url + " => " + serverResponse);

            if(!isStatusOk(connectionStatusCode))
                return null;

            return serverResponse;

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * This method downloads bitmap image
     * @param url address of image
     * @return bitmap
     * @throws IOException
     */
    public Bitmap downloadBitmap(String url) throws IOException {

        HttpUriRequest request = new HttpGet(url);
        HttpClient httpClient = new DefaultHttpClient();
        HttpResponse response = httpClient.execute(request);

        StatusLine statusLine = response.getStatusLine();
        int connectionStatusCode = statusLine.getStatusCode();
        if (isStatusOk(connectionStatusCode)) {
            HttpEntity entity = response.getEntity();
            byte[] bytes = EntityUtils.toByteArray(entity);

            Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
            return bitmap;
        } else {
            throw new IOException("Download failed, HTTP response code "
                    + connectionStatusCode + " - " + statusLine.getReasonPhrase());
        }
    }



    /**
     * Interface for an HTTP and HTTPS client.
     * @return HttpClient
     */
    private HttpClient getNewHttpClient() {
        try {
            KeyStore trustStore = KeyStore.getInstance(KeyStore.getDefaultType());
            trustStore.load(null, null);

            SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
            sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

            HttpParams params = new BasicHttpParams();
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, HTTP.UTF_8);

            SchemeRegistry registry = new SchemeRegistry();
            registry.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            registry.register(new Scheme("https", sf, 443));

            ClientConnectionManager ccm = new ThreadSafeClientConnManager(params, registry);

            return new DefaultHttpClient(ccm, params);
        } catch (Exception e) {
            return new DefaultHttpClient();
        }
    }

    /**
     * Connection status checker.
     * @param statusCode status code of connection
     * @return True if connection code is 2xx, False otherwise.
     */
    private static boolean isStatusOk(int statusCode) {
        return (statusCode >= HttpURLConnection.HTTP_OK) && (statusCode <= HttpURLConnection.HTTP_PARTIAL);
    }

    /******************************************************************
     * Customised SSLSocketFactory in order to configure SSL checking *
     ******************************************************************/
    public class MySSLSocketFactory extends SSLSocketFactory {
        SSLContext sslContext = SSLContext.getInstance("TLS");

        public MySSLSocketFactory(KeyStore trustStore) throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException, UnrecoverableKeyException {
            super(trustStore);

            TrustManager tm = new X509TrustManager() {
                public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
                }

                public X509Certificate[] getAcceptedIssuers() {
                    return null;
                }
            };

            sslContext.init(null, new TrustManager[] { tm }, null);
        }

        @Override
        public Socket createSocket(Socket socket, String host, int port, boolean autoClose) throws IOException, UnknownHostException {
            return sslContext.getSocketFactory().createSocket(socket, host, port, autoClose);
        }

        @Override
        public Socket createSocket() throws IOException {
            return sslContext.getSocketFactory().createSocket();
        }
    }
}
