package com.lagou.func2;

/*
*
*   建立一个test 目录然后执行下面的命令批量创建测试目录和相应的文件
*     for($i=1;$i -le 10;$i++)
    {
        mkdir test$i
        cd test$i
        for ($j=1;$j -le 10;$j++)
        {
            New-Item test_text_$j.txt
        }
        cd ..
    }
*
*
*
*  直接对家目录下面的test目录下面的所有的目录和文件全部进行删除
*
*
*
*
*
*
* 执行日志执行前
* PS K:\moudle04\test> ls



Mode                LastWriteTime         Length Name
----                -------------         ------ ----
d-----        2020/12/6     18:41                test1
d-----        2020/12/6     18:41                test2
d-----        2020/12/6     18:41                test3
d-----        2020/12/6     18:41                test5
d-----        2020/12/6     18:41                test6
d-----        2020/12/6     18:41                test7
d-----        2020/12/6     18:41                test8
d-----        2020/12/6     18:41                test9
d-----        2020/12/6     18:41                test10

*
*
*
*
*
* 执行后
* PS K:\moudle04> ls


    目录: K:\moudle04


Mode                LastWriteTime         Length Name
----                -------------         ------ ----
d-----        2020/12/4     14:19                .idea
d-----        2020/12/4     14:19                src
d-----        2020/12/6     15:53                out
-a----        2020/12/4     14:19            433 moudle04.iml
-a----        2020/12/6     18:43           2646 readme.md
-a----        2020/12/6     16:04            186 a.txt


* */


import java.io.File;

public class FileRemove {

    public static void main(String[] args) {
        //初始化一个路径
        File Dir = new File("test");
        //删除dir
        deleteDir(Dir);


    }

    public static void deleteDir(File dir){
/*
* 递归的去删除相应的文件
* 目录 -> 目录 -> 文件删除
*
* param: dir 一个路径
* */
//        声明一个当前路径下面的列表
        File[] fIles=dir.listFiles();
//        如果路径下不为空
            if (dir !=null){
//            循环执行将文件内容分别处理
                for (File f:fIles){
                    //判断是否是一个目录如果是目录就递归执行
                    if (f.isDirectory()){
//                        如果是dir则就重复执行方法
                        deleteDir(f);
                    }else{
                        //不是则就删除
                        f.delete();
                    }
                }
            }
        dir.delete();
    }







}




