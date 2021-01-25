import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.ByteArrayBuffer;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        try
        {
            String url="http://192.168.101.14/OA/getdata.ashx?ds=flow_mystart";
            String cookie="UserInfo=uName=xusheng&uPass=&uRememberName=1; ASP.NET_SessionId=t54nxyr5b5qngj2nz2rglh32; .ASPXAUTH=47BA24163178CB2BC8C5B41348D6ECAAC7CE5FA49B2083C1CC3651187D3283D2F3874F45E508465ABFDA4DFD2E945306BC73199AA5806168E325A3B77DBEF101ED355E9EF6717D643516B6627D044735F5BBF7385EDDB94F3D8F493FA98526C0ED5992DE2DC8300E6B188236";
            List<NameValuePair> nameValuePairs=new ArrayList<NameValuePair>();
            CloseableHttpClient httpClient= HttpClientBuilder.create().build();
            HttpPost httpPost=new HttpPost(url);
            httpPost.setHeader("cookie",cookie);
            httpPost.setHeader("Content-Type","application/x-www-form-urlencoded");
            httpPost.setHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");

            /*
            * form data
            * */
            nameValuePairs.add(new BasicNameValuePair("page","1"));
            nameValuePairs.add(new BasicNameValuePair("rp","12"));
            nameValuePairs.add(new BasicNameValuePair("sortname","_CreateTime"));
            nameValuePairs.add(new BasicNameValuePair("sortorder","desc"));
            nameValuePairs.add(new BasicNameValuePair("query",""));
            nameValuePairs.add(new BasicNameValuePair("qtype",""));
            nameValuePairs.add(new BasicNameValuePair("queryid","flowmystart"));
            nameValuePairs.add(new BasicNameValuePair("condition","_UserName='53c6b305-5bea-450a-b7a1-11ce1feab4b2'"));

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

            /*
             * xpath
             */
            Document doc=null;
            XPath xPath=null;
            DocumentBuilderFactory dbf=DocumentBuilderFactory.newDefaultInstance();
            dbf.setValidating(false);
            InputStream inputStreamxml=new ByteArrayInputStream(text.getBytes(StandardCharsets.UTF_8));
            DocumentBuilder db=dbf.newDocumentBuilder();
            doc=db.parse(inputStreamxml);
            XPathFactory factory=XPathFactory.newInstance();
            xPath=factory.newXPath();

            NodeList nodeList=(NodeList) xPath.evaluate("//*[name()='InstanceName']",doc, XPathConstants.NODESET);
            for(int i=0;i<nodeList.getLength();i++)
            {
                System.out.println(nodeList.item(i).getNodeName()+"-->"+nodeList.item(i).getTextContent());
            }

            //打印根节点下所有元素节点
            System.out.println(doc.getDocumentElement().getChildNodes().getLength());
            NodeList nodeList=doc.getDocumentElement().getChildNodes();
            for(int i=0;i<nodeList.getLength();i++)
            {
                if(nodeList.item(i).getNodeType()== Node.ELEMENT_NODE)
                {
                    System.out.println(nodeList.item(i).getNodeName()+"\t");
                }
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
