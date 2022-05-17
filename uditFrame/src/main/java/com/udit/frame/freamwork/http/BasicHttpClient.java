/**
 * 
 */
package com.udit.frame.freamwork.http;

import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRoute;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.scheme.SocketFactory;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
/**
 * 
 * httpClient 连接基础类 
 * @author 曾宝
 * @version  [V1.00, 2015年7月14日]
 * @see  [相关类/方法]
 * @since V1.00
 */
public class BasicHttpClient
{
    private static DefaultHttpClient hc = null;
    /**
     * HTTP 超时时间
     */
    private static int OUT_TIME = 5*1000;
    
    public static DefaultHttpClient getHttpClient()
    {
        if (null == hc)
        {
            hc = getClient();
            
        }
        
        return hc;
    }
    
    private static DefaultHttpClient getClient()
    {
        HttpParams params = new BasicHttpParams();
        //设置并发数
        ConnPerRoute connPerRoute = new ConnPerRouteBean(100);
        ConnManagerParams.setMaxConnectionsPerRoute(params, connPerRoute);
        ConnManagerParams.setMaxTotalConnections(params, 100);
        
        //状态检查
        HttpConnectionParams.setStaleCheckingEnabled(params, false);
        
        // 设置协议
        final SchemeRegistry supportedSchemes = new SchemeRegistry();
        
       
        HttpConnectionParams.setConnectionTimeout(params, OUT_TIME);
        //20 S 如果没收到数据 强制断开
        HttpConnectionParams.setSoTimeout(params, 60 * 1000);
        //缓存
        HttpConnectionParams.setSocketBufferSize(params, 8192);
        
      
        final SocketFactory sf = PlainSocketFactory.getSocketFactory();
        //设置套接字
        supportedSchemes.register(new Scheme("http", sf, 80));
        supportedSchemes.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443)); 
        
        final ClientConnectionManager ccm = new ThreadSafeClientConnManager(
                params, supportedSchemes);
        DefaultHttpClient httpClient = new DefaultHttpClient(ccm, params);
        
        return httpClient;
    }
    
    public static void clearHttpClient()
    {
        if (hc != null)
        {
            hc = null;
        }
    }
}
