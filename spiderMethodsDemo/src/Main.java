public class Main {
    public static void main(String[] args) throws Exception {
        /**
         * 调用Head
         Head head=new Head();
         head.setUrl("http://192.168.101.14/oa/");
         head.doHead();
         */

        /**
         * 调用Get
         *         Get get=new Get();
         *         get.setUrl("http://www.weather.com.cn/weathern/101030400.shtml?");
         *         get.addHeader("User-Agent","Mozilla/5.0 (X11; Linux x86_64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");
         *         get.addHeader("Cookie","HttpOnly; UM_distinctid=176d650b687fc-0a035a289264ec-1e2e1b0b-1fa400-176d650b688c77; Hm_lvt_080dabacb001ad3dc8b9b9049b36d43b=1609913383; f_city=%E5%A4%A9%E6%B4%A5%7C101030100%7C; Wa_lvt_1=1609913383; CNZZDATA1278536588=1934037795-1609908526-%7C1609918974");
         *         System.out.println(get.doGet());
         */


        /**
         * 调用Post

         Post post=new Post();
         post.setUrl("http://192.168.101.14/OA/getdata.ashx?ds=flow_mystart");
         post.setCookie("UserInfo=uName=xusheng&uPass=&uRememberName=1; ASP.NET_SessionId=rz2q1vwssxnylgul24m4sgke; .ASPXAUTH=74581D336BA2FF22745398E8111C4BEF7C534F57653BC5598398C0AD003D0B9806875C1693B1EDE7BBA470F1D20EE4148EC2F7E4E8C0623552A99514AEFDE92502F8F82E328A3C21B0D18EBBA22F7720F0A5CFDC8E4A3B2ACC6E75BDA877E08FBB54E70F28A6548552EFD97D");
         post.addHeader("Content-Type","application/x-www-form-urlencoded");
         post.addHeader("User-Agent","Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/87.0.4280.88 Safari/537.36");

         post.addNameValuePairs("page","1");
         post.addNameValuePairs("rp","12");
         post.addNameValuePairs("sortname","_CreateTime");
         post.addNameValuePairs("sortorder","desc");
         post.addNameValuePairs("query","");
         post.addNameValuePairs("qtype","");
         post.addNameValuePairs("queryid","flowmystart");
         post.addNameValuePairs("condition","_UserName='53c6b305-5bea-450a-b7a1-11ce1feab4b2'");

         post.doPost();

         * */



    }
}

