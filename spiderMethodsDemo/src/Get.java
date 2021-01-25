import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Get {
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
    private Map header=new HashMap();
    public void addHeader(String key,String value)
    {
        header.put(key,value);
    }

    private String html="";
    public String doGet() throws Exception
    {
        CloseableHttpClient closeableHttpClient= HttpClientBuilder.create().build();
        HttpGet httpGet=new HttpGet(url);
//        httpGet.addHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
//        httpGet.setHeader("Cookie","HttpOnly; UM_distinctid=176d650b687fc-0a035a289264ec-1e2e1b0b-1fa400-176d650b688c77; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1609913383; f_city=%E5%A4%A9%E6%B4%A5%7C101030100%7C; Wa_lvt_1=1609913383; CNZZDATA1278536588=1934037795-1609908526-%7C1609918974");
        Set<Map.Entry<String,String>> entrySet=header.entrySet();
        for(Map.Entry<String,String> entryset:entrySet)
        {
            httpGet.setHeader(entryset.getKey(),entryset.getValue());
        }

        HttpResponse response=closeableHttpClient.execute(httpGet);
        HttpEntity entity=response.getEntity();
        if(entity!=null)
        {
            html= EntityUtils.toString(entity,"UTF-8");
            EntityUtils.consume(entity);
        }
        return html;
    }
}
