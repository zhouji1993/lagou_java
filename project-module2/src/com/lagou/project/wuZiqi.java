package com.lagou.project;

/**
 * 五子棋：
 * 首先是在展示部分打印一个16 * 16 的棋盘
 *
 * 由于是五子棋，需要两个人都需要进行下棋才能确定胜利的标准
 * 故 步长应该大于等于10 setp >=10
 * 也就是大于10步开始可以对于下棋的胜负进行判别
 * 增加认输的功能
 *
 * */






import java.util.HashMap;
import java.util.Scanner;

public class wuZiqi {
    private static Scanner sc;
    private static String num;
    private  char[] midarry= {'0','1','2','3','4','5','6','7','8','9','a','b','c','d','e','f'};

    private char[][] midarryb= new char[16][16];
    private int size = midarry.length;




    private HashMap<Character , Integer> hMap = new HashMap<Character ,Integer>();
//定义元素索引列表
    {
        for (int i = 0; i < midarry.length; i++) {
            hMap.put(midarry[i], i);

        }
    }






    char[][] InitQiPan( ){

        for (int i = 0 ; i < size  ; i++ ){
            for (int j = 0;j <size ;j++){
                if (i==0 & j==0 ){
                    midarryb[i][j]=' ';
                }
                if (j==0 & i!=0){
                    midarryb[i][j] =midarry[i];

                }
                if (i==0 & j!=0){
                    midarryb[i][j]=midarry[j];
                }
                if (i!=0 & j!=0){
                    midarryb[i][j]='+';
                }


            }
        }
        return midarryb;
    }

    void ShowQipan(char[][] s){

        for (int a =0  ; a < s.length ; a++){
            StringBuffer midstr= new StringBuffer();

            for (int b =0  ; b < s[a].length ; b++){
                    midstr.append(" "+ s[a][b]);

            }
            System.out.println(midstr);
        }

    }


    public char[][] ChangeMidarrya(String a,char i) {
        
        char[] midc=a.toCharArray();
        int xvalue=hMap.get(midc[0]);
        int yvalue=hMap.get(midc[1]);
        midarryb[xvalue][yvalue]=i;
        return midarryb;
    }


    public boolean autowin(String a, char i) {

        /*
         * 横竖斜都进行判断对数据进行一次判断
         * 建立一个数组来保留棋盘棋子的状态
         * 循环从[-4,4]这个区间的所有元素
         * 相同的符号+1，不同的符号将对应的数组修改成1
         * 注：需要对边界进行判断不能超出边界
         * 排序看最大的元素是否大于或等于5
         * 是则返回true
         * 不是则返回false
         *
         * */

        char[] midc = a.toCharArray();
        int xvalue = hMap.get(midc[0]);
        int yvalue = hMap.get(midc[1]);
        int[] dp = new int[]{0, 0, 0, 0};


        for (int s = -4; s < 5; s++) {
            if ((xvalue + s) >= 0 && (xvalue + s) <= 16 && midarryb[xvalue + s][yvalue] == i) {

                dp[0]++;
                if (dp[0] >= 5) {
                    return true;
                }
            } else {
                dp[0] = 0;
            }

            if ((yvalue + s) <= 16 && (yvalue + s) >= 0 && midarryb[xvalue][yvalue + s] == i) {
                dp[1]++;
                if (dp[1] >= 5) {
                    return true;
                }
            } else {
                dp[1] = 0;
            }
            if ((yvalue + s) >= 0 && (xvalue + s) >= 0 && (xvalue + s) <= 16 && (yvalue + s) <= 16 && midarryb[xvalue + s][yvalue + s] == i) {
                dp[2]++;
                if (dp[2] >= 5) {
                    return true;
                }
            } else {
                dp[2] = 0;
            }
            if ((yvalue + s) >= 0 && (xvalue - s) >= 0 && (xvalue - s) <= 16 && (yvalue + s) <= 16 && midarryb[xvalue - s][yvalue + s] == i) {
                dp[3]++;
                if (dp[3] >= 5) {
                    return true;
                }
            } else {
                dp[3] = 0;
            }


        }
        return false;
    }



    boolean exec(String num,char i){
        ChangeMidarrya(num, i);
        return autowin(num,i);
    }




    public static void main(String[] args) {
        wuZiqi wzq = new wuZiqi();
        char[][] qipan = wzq.InitQiPan();
        wzq.ShowQipan(qipan);
        // 初始化棋盘完毕
        //由于五子棋下棋来说需要第九步才能知晓胜利的与否，在这里初始化一个变量步长作为使用
        System.out.println("棋盘初始化完毕");
        int setp =0;
        boolean status=false;
        char winner = ' ';
        while (status != true) {
            sc = new Scanner(System.in);
            try {
                num=sc.nextLine();
            }
            catch (java.lang.ArrayIndexOutOfBoundsException e){
                System.out.println("不能为空请重新输入");
            };



            if ( setp % 2 == 0) {
                status=wzq.exec(num,'o');
                winner='o';
            } else {
                status=wzq.exec(num,'x');
                winner='x';
            }
            wzq.ShowQipan(qipan);
            setp++;

        }
        System.out.println("比赛结束,胜利者："+winner);

    }
}
