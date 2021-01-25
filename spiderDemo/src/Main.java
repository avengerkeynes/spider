import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        GetOAData getOAData=new GetOAData();
        Dcookie dcookie=new Dcookie();
        getOAData.setUrl("http://192.168.101.14/OA/getdata.ashx?ds=flow_mystart");
        getOAData.setCookie(dcookie.cookie);
        getOAData.addHeader("Content-Type",dcookie.contentType);
        getOAData.addHeader("User-Agent",dcookie.userAgent);

        /*赋参数*/
       getOAData.addNamePairs("page","1");
        getOAData.addNamePairs("rp","12");
        getOAData.addNamePairs("sortname","_CreateTime");
        getOAData.addNamePairs("sortorder","desc");
        getOAData.addNamePairs("query","");
        getOAData.addNamePairs("qtype","");
        getOAData.addNamePairs("queryid","flowmystart");
        getOAData.addNamePairs("condition","_UserName='53c6b305-5bea-450a-b7a1-11ce1feab4b2'");

        regular r=new regular();
        r.setContent(getOAData.post());

       String[] page=getOAData.get(r.operationData(),dcookie.cookie,dcookie.contentType,dcookie.userAgent);
       ArrayList<String> d=r.calPage(page);
       InsertIntoDB insert=new InsertIntoDB();
       insert.insert(d);
        System.out.println("完成");
    }
}

