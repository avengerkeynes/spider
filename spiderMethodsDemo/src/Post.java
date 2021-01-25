import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;

public class Post {
    private String url="";
    public void setUrl(String url)
    {
        this.url=url;
    }
    private String cookie="";
    public void setCookie(String cookie)
    {
        this.cookie=cookie;
    }
    private Map<String,String> header=new HashMap<>();

    public void addHeader(String key,String value) {
        header.put(key,value);
    }

    private List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
    public void addNameValuePairs(String key,String value)
    {
        nameValuePairs.add(new BasicNameValuePair(key,value));
    }

    public String doPost() throws Exception
    {
        CloseableHttpClient httpClient= HttpClientBuilder.create().build();
        HttpPost httpPost=new HttpPost(url);

        Set<Map.Entry<String,String>> entrySet=header.entrySet();
        for(Map.Entry<String,String> entryset:entrySet)
        {
            httpPost.setHeader(entryset.getKey(),entryset.getValue());
        }
//        httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
//        httpPost.setHeader("Cookie",cookie);
//        nameValuePairs.add(new BasicNameValuePair("page","1"));
//        nameValuePairs.add(new BasicNameValuePair("rp","12"));
//        nameValuePairs.add(new BasicNameValuePair("sortname","_CreateTime"));
//        nameValuePairs.add(new BasicNameValuePair("sortorder","desc"));
//        nameValuePairs.add(new BasicNameValuePair("query",""));
//        nameValuePairs.add(new BasicNameValuePair("qtype",""));
//        nameValuePairs.add(new BasicNameValuePair("queryid","flowmystart"));
//        nameValuePairs.add(new BasicNameValuePair("condition","_UserName='53c6b305-5bea-450a-b7a1-11ce1feab4b2'"));
        httpPost.setHeader("cookie",cookie);
        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
        HttpResponse response=httpClient.execute(httpPost);
        HttpEntity entity=response.getEntity();
        InputStream inputStream=entity.getContent();
        BufferedInputStream bufferedInputStream=new BufferedInputStream(inputStream);
        ByteArrayBuffer byteArrayBuffer=new ByteArrayBuffer(20);
        int current=0;
        while((current=bufferedInputStream.read())!=-1)
        {
            byteArrayBuffer.append((byte) current);
        }
        String text=new String(byteArrayBuffer.toByteArray());
        System.out.println(text);
        return text;
    }
}

