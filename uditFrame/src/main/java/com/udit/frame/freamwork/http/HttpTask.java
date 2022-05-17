package com.udit.frame.freamwork.http;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;





import android.os.AsyncTask;

import com.udit.frame.freamwork.activity.BaseActivity;
import com.udit.frame.freamwork.activity.BaseApplication;
import com.udit.frame.utils.MyLogUtils;
import com.udit.frame.utils.MyNetUtils;
import com.udit.frame.utils.ToastUtil;
import com.udit.frame.utils.Utils;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

public class HttpTask extends AsyncTask<HashMap<String, String>, Integer, String>
{
    /**
     * DEBUG_TAG
     */
    private static final String TAG = "HttpTask";

    /**
     * POST 请求
     */
    public static int REQUEST_TYPE_POST = 0;
    
    /**
     * GET 请求
     */
    public static int REQUEST_TYPE_GET = 1;
    
    /**
     * URL链接
     */
    private String mUrl;
    
    /**
     * Http_Response 返回处理
     */
    private IHttpResponseListener mResponseListener;
    
    /**
     * request 类型
     */
    private int mRequestType = 0;
    
    /**
     * Context;
     */
    // private Context mContext;
    
    /**
     * Http返回
     */
    @SuppressWarnings("unused")
    private RequestObject mRequestObject;
    
    //false 出错信息，true 正常返回
    private boolean flag = false;
    
    @Override
    protected void onPostExecute(String result)
    {
        if(flag)
        {
            mResponseListener.doHttpResponse(result);
        }
        else
        {
            mResponseListener.onError(result);
        }
        super.onPostExecute(result);
    }
    
    /**
     * 建立连接
     * 重写方法
     * 
     * @param params
     * @return
     * @see AsyncTask#doInBackground(Object[])
     */
    @SuppressWarnings("unchecked")
    @Override
    protected String doInBackground(HashMap<String, String>... params)
    {
        
        HashMap<String, String> requestHashMap = params[0];
        String JSONString = "";
        HttpPost httpPost = null;
        HttpGet httpGet = null;
        MyLogUtils.e(TAG, "url：" + this.mUrl);
        boolean flags =  MyNetUtils.checkNetwork(BaseApplication.getInstance());
        if(!flags)
        {
            flag = false;
            ToastUtil.showMessage(BaseApplication.getAppContext(),"网络异常，请检查网络");
            return "网络异常，请检查网络";
        }


        // request_post 请求
        if (mRequestType == REQUEST_TYPE_POST)
        {
            httpPost = new HttpPost(this.mUrl);
            httpPost.addHeader("user-agent", "android");
        }
        else
        {
            try
            {
                httpGet = new HttpGet(this.mUrl);
                httpGet.addHeader("user-agent", "android");
            }
            catch (Exception e)
            {
                MyLogUtils.d(TAG, "httpGet is err" + e.toString());
                flag = false;
                return "请求超时";
            }
        }
        // 请求参数绑定
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        if (requestHashMap != null)
        {
            Set<String> set = requestHashMap.keySet();
            for (Iterator<String> iterator = set.iterator(); iterator.hasNext();)
            {
                String object = iterator.next();
                formparams.add(new BasicNameValuePair(object, (String)requestHashMap.get(object)));
                
            }
            MyLogUtils.e(TAG, requestHashMap.toString());
        }
        // 发起请求
        try
        {
            HttpClient httpClient = BasicHttpClient.getHttpClient();
            HttpResponse httpResponse = null;
            
            if (mRequestType == REQUEST_TYPE_POST)
            {
                // post 请求
                httpPost.setEntity(new UrlEncodedFormEntity(formparams, HTTP.UTF_8));// utf-8
                httpResponse = httpClient.execute(httpPost);
            }
            else
            {
                Iterator<Map.Entry<String, String>> iterator = requestHashMap.entrySet().iterator();
                String parmastr = "";
                while (iterator.hasNext())
                {
                    Map.Entry<String, String> entry = iterator.next();
                    parmastr+= entry.getKey() + "=" + entry.getValue() + "&";
                }
                if (parmastr != null && parmastr.length() != 0)
                {
                    parmastr = parmastr.substring(0, parmastr.length() - 1);
                }
                
                parmastr = httpGet.getURI().toString() + "?" + parmastr;
                MyLogUtils.e(TAG, parmastr);
                httpGet.setURI(URI.create(parmastr));
                httpResponse = httpClient.execute(httpGet);
            }
            
            // 服务器响应
            if (httpResponse.getStatusLine().getStatusCode() == 200)
            {
                
                JSONString = getJsonString(httpResponse);
                JSONString = Utils.unicode2string(JSONString);
                MyLogUtils.e(TAG, JSONString);
                
                @SuppressWarnings("unused")
                Header[] s = httpResponse.getHeaders("Set-Cookie");
                flag = true;
                return JSONString;
               // mResponseListener.doHttpResponse(JSONString);
            }
            else
            {// 响应出错
                MyLogUtils.e(TAG, "Error Response: " + httpResponse.getStatusLine().toString());
              //  mResponseListener.onError(httpResponse.getStatusLine().toString());
                flag = false;
                return httpResponse.getStatusLine().toString();
            }
        }
        catch (ConnectTimeoutException e)
        {
            MyLogUtils.d(TAG, "ConnectTimeoutException :" + e);
            // HTTPCONNECT_ERROR_TIMEOUT_STR
           // mResponseListener.onError("请求超时");
            flag = false;
            return "请求超时";
//            return null;
        }
        catch (SocketTimeoutException e)
        {
            MyLogUtils.d(TAG, "SocketTimeoutException :" + e);
            flag = false;
            return "请求超时";
     /*       mResponseListener.onError("请求超时");
            return null;*/
        }
        catch (Exception e)
        {
            e.printStackTrace();
            MyLogUtils.d(TAG, "异常 " + e);
            flag = false;
            return "请求超时";
           /* mResponseListener.onError("请求超时");
            return null;*/
        }
        finally
        {
            if (mRequestType == REQUEST_TYPE_POST)
            {
                httpPost.abort();
            }
            else
            {
                httpGet.abort();
            }
        }
    }
    
    /**
     * 开启请求
     * <功能详细描述>
     * 
     * @param requestObject
     * @param requestType
     * @param listener
     * @return
     * @see [类、类#方法、类#成员]
     */
    @SuppressWarnings("unchecked")
    public HttpTask start(final RequestObject requestObject, int requestType, IHttpResponseListener listener)
    {
        // this.mContext = context;
        mResponseListener = listener;
        if (requestObject == null)
        {
            return null;
        }
        
        /*
         * if (!MyNetUtils.checkNetwork(requestObject.mContext))
         * {
         * // 无网络
         * listener.onError("无网络，检查网络");
         * return null;
         * }
         */
        
        mRequestType = requestType;
        mUrl = requestObject.uriAPI;
        mRequestObject = requestObject;
        
        execute(requestObject.requestHashMap);
        return this;
    }
    
    /**
     * 返回页面的内容
     * <功能详细描述>
     * 
     * @param response
     * @return
     * @see [类、类#方法、类#成员]
     */
    private String getJsonString(HttpResponse response)
    {
        try
        {
            
            StringBuilder builder = new StringBuilder();
            
            HttpEntity resEntity = response.getEntity();
            BufferedReader reader = new BufferedReader(new InputStreamReader(resEntity.getContent()));
            for (String s = reader.readLine(); s != null; s = reader.readLine())
            {
                builder.append(s);
            }
            
          //  MyLogUtils.d(TAG, "data from server:" + builder.toString());
            return builder.toString();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
}
