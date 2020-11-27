package com.lagou.func3;

//執行結果
//"C:\Program Files\Java\jdk1.8.0_261\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\lib\idea_rt.jar=55591:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_261\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\rt.jar;K:\model3\out\production\model3" com.lagou.func3.countString
//        键值123:2
//        键值456:2
//        键值789:1
//
//        Process finished with exit code 0



import java.util.HashMap;
import java.util.Iterator;

public class countString {






    public static void main(String[] args) {
        String words ="123,456,789,123,456";
        HashMap<String, Integer> counts = new HashMap<String, Integer>( );
        String[] split = words.split(",");
        for (String string:split){
            if (counts.containsKey(string)){
                counts.put(string,counts.get(string)+1);
            }else
            {
                counts.put(string, 1);
            }

        }



        Iterator iter = counts.entrySet().iterator();
        for (String key : counts.keySet()) {
            System.out.println("键值"+ key + ":"+counts.get(key));

        }















    }




}
