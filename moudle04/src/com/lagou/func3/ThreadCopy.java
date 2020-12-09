package com.lagou.func3;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*
*
* 3. 编程题

  使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容。
 *
*
* */
public class ThreadCopy {
    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.submit(copyFile("mid_dir","desc_dir"));
        executorService.shutdown();





    }
    public static Runnable copyFile(String src, String desc) throws Exception {
        /*
         * 循环复制文件
         * @param src String 原始路径 如 c:/src
         * @param desc String 目标路径 如 d:/desc
         * @return boolean
         *
         * */
        File resourceFile = new File(src);
        if (!resourceFile.exists()) {
            throw new Exception("源目标路径：[" + src + "] 不存在...");
        }
        File targetFile = new File(desc);
        if (!targetFile.exists()) {
            throw new Exception("存放的目标路径：[" + desc + "] 不存在...");
        }
        File srcDir = new File(src);
        File descDir = new File(desc);

        File[] srcFile = srcDir.listFiles();
        for (File file:srcFile){
            File file1=new File(descDir.getAbsolutePath() + File.separator + srcDir.getName());
            if (file.isFile()){
                System.out.println("文件" + file.getName());
                // 在 目标文件夹（B） 中 新建 源文件夹（A），然后将文件复制到 A 中
                // 这样 在 B 中 就存在 A
                if (!file1.exists()) {
                    file1.mkdirs();
                }
                File targetFile1 = new File(file1.getAbsolutePath() + File.separator + file.getName());
                copyFile(file.getPath(), targetFile1.getPath());
            }
            if (file.isDirectory()){
                String dir1 = file.getAbsolutePath();
                String dir2 = file1.getAbsolutePath();
                copyFile(dir1,dir2);

            }

        }


        return null;
    }


}
