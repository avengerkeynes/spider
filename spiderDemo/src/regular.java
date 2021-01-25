import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class regular {
    private String content="";
    public void setContent(String content)
    {
        this.content=content;
    }
    private ArrayList<String> urls=new ArrayList<>();
    public ArrayList<String> operationData()
    {
        Pattern pattern=Pattern.compile("<row id=\".*?\"><rowindex>");
        Matcher matcher=pattern.matcher(content);
        while(matcher.find())
        {
            urls.add("http://192.168.101.14/OA/Sysfolder/AppFrame/AppWorkFlowInfo.aspx?InstanceId="+matcher.group().replace("<row id=\"","").replace("\"><rowindex>",""));
        }
        return urls;
    }
    public ArrayList<String> calPage(String[] pages)
    {
        ArrayList<String> sql=new ArrayList<>();
        for(int i=0;i<pages.length;i++)
        {
            Document document= Jsoup.parse(pages[i]);
            Element mainDiv=document.getElementById("maindiv");
            String rwmc=mainDiv.select("h4").toString();
            Pattern pattern=Pattern.compile("</span>.*?</h4>");
            Matcher matcher=pattern.matcher(rwmc);
            if(matcher.find())
            {
                rwmc=matcher.group();
            }
            rwmc=rwmc.replace("</span>","");
            rwmc=rwmc.replace("</h4>","");


            Elements wfdeal=mainDiv.getElementsByClass("wfdealinfo");
            Elements tr=wfdeal.select("tr");
            for(Element tds:tr)
            {

                Pattern pattern_td=Pattern.compile(">.*?<");
                Matcher matcher_td=pattern_td.matcher(tds.toString());
                ArrayList<String> t=new ArrayList<>();
                while(matcher_td.find())
                {
                    t.add(matcher_td.group());
                }
                if(t.size()==11)
                {
//                    System.out.println("步骤名称："+t.get(3));
//                    System.out.println("处理人："+t.get(5));
//                    System.out.println("审批："+t.get(7));
//                    System.out.println("处理意见:"+t.get(8));
//                    System.out.println("查看时间："+t.get(9));
//                    System.out.println("处理时间："+t.get(10));
                    sql.add("INSERT INTO result VALUES('"+rwmc+"','"+t.get(3).replace("<","").replace(">","")+"','"+t.get(5).replace("<","").replace(">","")+"','"+t.get(7).replace("<","").replace(">","")+"','"+t.get(8).replace("<","").replace(">","")+"','"+t.get(9).replace("<","").replace(">","")+"','"+t.get(10).replace("<","").replace(">","")+"')");
                }
            }
        }
        return sql;
    }
}

