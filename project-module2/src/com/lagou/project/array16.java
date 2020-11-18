package com.lagou.project;
/**
 *觉得这个东西应该加入另一个参数作为函数调用时可以对函数对应字段的计算
 *
 *
 *
 * */
//题目描述
//定义一个长度为[16][16]的整型二维数组并输入或指定所有位置的元素值，分别实现二维数组中所有行和所有列中所有元素的累加和并打印。
//
//        再分别实现二维数组中左上角到右下角和右上角到左下角所有元素的累加和并打印。


/* 执行结果
* "C:\Program Files\Java\jdk1.8.0_261\bin\java.exe" "-javaagent:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\lib\idea_rt.jar=64672:D:\Program Files\JetBrains\IntelliJ IDEA 2020.2.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_261\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_261\jre\lib\rt.jar;E:\javase\out\production\javase" com.lagou.project1.array16
[27, 38, 26, 9, 40, 29, 1, 72, 19, 43, 39, 61, 61, 22, 72, 24]
[63, 75, 89, 62, 13, 86, 64, 35, 25, 89, 76, 77, 61, 2, 99, 29]
[2, 8, 70, 49, 91, 8, 84, 56, 59, 19, 70, 95, 89, 67, 2, 89]
[27, 24, 62, 84, 34, 83, 22, 91, 68, 34, 49, 93, 51, 68, 3, 2]
[35, 79, 95, 97, 12, 33, 91, 92, 54, 28, 67, 93, 68, 42, 82, 79]
[80, 17, 54, 78, 29, 28, 25, 97, 53, 96, 48, 58, 16, 75, 1, 12]
[43, 86, 10, 24, 1, 73, 14, 22, 98, 53, 12, 43, 42, 58, 2, 40]
[16, 25, 36, 15, 66, 27, 32, 96, 80, 97, 94, 6, 4, 92, 51, 65]
[16, 10, 39, 83, 72, 88, 43, 8, 33, 54, 38, 83, 41, 61, 32, 67]
[6, 37, 91, 58, 31, 73, 58, 34, 49, 96, 95, 18, 53, 13, 36, 7]
[28, 3, 83, 12, 61, 29, 90, 74, 4, 63, 83, 56, 63, 66, 29, 65]
[50, 79, 71, 84, 16, 74, 81, 53, 16, 23, 18, 2, 77, 19, 80, 66]
[23, 75, 80, 47, 19, 4, 91, 91, 69, 61, 33, 28, 99, 2, 31, 96]
[33, 89, 81, 76, 15, 1, 54, 9, 38, 48, 54, 68, 91, 25, 37, 17]
[84, 37, 59, 37, 39, 50, 60, 16, 90, 74, 15, 60, 76, 40, 21, 2]
[40, 61, 34, 21, 24, 66, 24, 70, 67, 1, 67, 50, 57, 36, 83, 90]
数据生成完成
左上角到右下角结果是：855
右上角到左下角结果是：1683

*
*
* */
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Random;

public class array16 {
    private int inplace =0;



    public static void main(String[] args) {
        array16 test = new array16();
        int[][] ares=test.initArrays();
        for (int a =0  ; a < ares.length ; a++)
            System.out.println(Arrays.toString(ares[a]));
        System.out.println("数据生成完成");
        test.MetriesFunc(ares);
    }


    int[][] initArrays(){
        Random rso=setRsi();

        int[][] arrays= new int[16][16];
        for(int n=0 ;n<16 ; n++){
            for(int m=0 ; m<16;m++){
                arrays[n][m] = getRsi(rso);

            }
        }

        return arrays ;
    }


    public int getRsi(Random rsi) {

        return rsi.nextInt(99)+1 ;
    }

    public Random setRsi() {
        Random rsi = new Random();
        return rsi;
    }

    public void  MetriesFunc(int[][] Arrays){
        int ans=0;
        for (int i =0 ;i < Arrays.length;i++) {
            ans += Arrays[i][i];

        }
        System.out.println("左上角到右下角结果是："+ ans);
        for (int i =Arrays.length -1 ;i > 0 ;i--) {
            ans += Arrays[i][i];

        }
        System.out.println("右上角到左下角结果是："+ ans);

   }



}
