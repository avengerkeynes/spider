import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpHead;

import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import java.net.URI;

public class Head {
    private String url="";
    public void setUrl(String url)
    {
        this.url=url;
    }
    public void doHead() throws Exception
    {
        CloseableHttpClient closeableHttpClient= HttpClientBuilder.create().build();
        URI uri=new URI(url);
        HttpHead method=new HttpHead(uri);
        HttpResponse response=closeableHttpClient.execute(method);

        Header[] s=response.getAllHeaders();

        for(int i=0;i<s.length;i++)
        {
            Header hd=s[i];
            System.out.println("Header Name: "+hd.getName()+"    "+"Header Value: "+hd.getValue());
        }

    }
}

