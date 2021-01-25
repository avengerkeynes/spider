import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.apache.http.util.EntityUtils;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.*;

public class GetOAData {

    private String url="";          //url
    public void setUrl(String url)
    {
        this.url=url;
    }

    private String cookie="";           //cookie
    public void setCookie(String cookie)
    {
        this.cookie=cookie;
    }

    private Map<String,String> header=new HashMap<>();

    public void addHeader(String key,String value)
    {
        header.put(key,value);
    }
    private List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
    public void addNamePairs(String key,String value)
    {
        nameValuePairs.add(new BasicNameValuePair(key,value));
    }


    public String post()
    {
        String text="";
        try
        {
            CloseableHttpClient httpClient= HttpClientBuilder.create().build();
            HttpPost httpPost=new HttpPost(url);
            Set<Map.Entry<String,String>> entrySet=header.entrySet();
            for(Map.Entry<String,String> entryset:entrySet)
            {
                httpPost.setHeader(entryset.getKey(),entryset.getValue());
            }
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
            text=new String(byteArrayBuffer.toByteArray());
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return text;
    }

    public String[] get(ArrayList<String> urls,String cookie,String contentType,String userAgent)
    {
        String[] html=new String[urls.size()];
        try
        {
            for(int i=0;i<urls.size();i++)
            {
                String url=urls.get(i);
                CloseableHttpClient closeableHttpClient=HttpClientBuilder.create().build();
                HttpGet httpGet=new HttpGet(url);
                httpGet.setHeader("Cookie",cookie);
                httpGet.setHeader("User-Agent",userAgent);
                httpGet.setHeader("Content-Type",contentType);
                HttpResponse response=closeableHttpClient.execute(httpGet);
                HttpEntity entity=response.getEntity();
                if(entity!=null)
                {
                    html[i]= EntityUtils.toString(entity,"UTF-8");
                    EntityUtils.consume(entity);
                }

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return html;
    }
}

