package com.lagou.func2;



//執行結果
//"C:\Program Files\Java\jdk1.8.0_261\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\lib\idea_rt.jar=55670:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_261\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\rt.jar;K:\model3\out\production\model3" com.lagou.func2.SameString
//        asd
//
//        Process finished with exit code 0




public class SameString {

    public static String maxSubstring(String ones, String twos) {

        if (ones == null || twos ==null){
            return null;
        }
        if (ones.equals("") || twos.equals("")){
            return null;
        }
        String max = "";
        String min = "";

        if (ones.length() < twos.length()){
            max= twos;
            min = ones;

        }else {
            max = ones;
            min = twos;

        }
        String current = "";
//        System.out.println("max:"+max + "\nmin:"+ min);

        for (int i=0;i<min.length();i++){


            for (int begin =0 ,end = min.length() -i; end<min.length(); begin++ ,end++){
//                System.out.println("begin:"+ begin);
//                System.out.println("end:"+ end);

                current = min.substring(begin,end);
//                System.out.println("current:" + current);
                if (max.contains(current)){
                    return current;
                }

            }

        }
        return null;








    }



    public static void main(String[] args) {
        String s1="asdafghjka";
        String s2="aaasdfg";
        String result = SameString.maxSubstring(s1,s2);
        System.out.println(result);


    }

}
