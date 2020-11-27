package com.lagou.func1;
//執行結果
//"C:\Program Files\Java\jdk1.8.0_261\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\lib\idea_rt.jar=55642:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_261\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\rt.jar;K:\model3\out\production\model3" com.lagou.func1.CounterChars
//        键值Upper
//        数量4
//        符号：ABCD
//        键值Lower
//        数量2
//        符号：ab
//        键值Sepc
//        数量5
//        符号：!@#$%
//        键值Number
//        数量3
//        符号：123
//
//        Process finished with exit code 0





import java.util.HashMap;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CounterChars {
    private String testStrings = "ABCD123!@#$%ab";
     HashMap<String, String> counts = new HashMap<String, String>( );




    public void setCounts(HashMap<String, String> counts) {
        counts.put("Lower","[a-z]");
        counts.put("Upper","[A-Z]");
        counts.put("Number","[0-9]");
        counts.put("Sepc","[^a-zA-Z0-9]");
        this.counts = counts;
    }

    public HashMap<String, String> getCounts() {
        return counts;
    }

    public void setTestStrings(String testStrings) {
        this.testStrings = testStrings;
    }

    public String getTestStrings() {
        return testStrings;
    }

    void  funcS(HashMap<String, String> counts){
        Iterator iter = counts.entrySet().iterator();
        for (String key : counts.keySet()){
            String value = counts.get(key);
            Pattern p = Pattern.compile(value);
            Matcher pm = p.matcher(getTestStrings());
            int counter = 0;
            StringBuilder Midstr=new StringBuilder();
            while (pm.find()){
                Midstr.append(pm.group(0));
                counter++;
            }
            System.out.println("键值"+key  + "\n数量"+counter +"\n符号："+ Midstr );




        }

    }



    public static void main(String[] args) {

        CounterChars c = new CounterChars();
        HashMap<String, String> hmc= c.counts;
        c.setCounts(hmc);
        c.funcS(c.getCounts());

    }

}
