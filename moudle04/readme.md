1. 编程题 

  基于学生信息管理系统增加以下两个功能： 

            a.自定义学号异常类和年龄异常类，并在该成员变量不合理时产生异常对象并抛出。 

            b.当系统退出时将 List 集合中所有学生信息写入到文件中，当系统启动时读取文件中所 有学生信息到 List 集合中。

2. 编程题 

  实现将指定目录中的所有内容删除，包含子目录中的内容都要全部删除。 
    初始化命令
    for($i=1;$i -le 10;$i++)
    {
        mkdir test$i
        cd test$i
        for ($j=1;$j -le 10;$j++)
        {
            New-Item test_text_$j.txt
        }
        cd ..
    }
    

    

3. 编程题 

  使用线程池将一个目录中的所有内容拷贝到另外一个目录中，包含子目录中的内容。 

4. 编程题 

  使用基于 tcp 协议的编程模型实现将 UserMessage 类型对象由客户端发送给服务器； 

  服 务 器接 收到 对象 后判 断 用户 对象 信息 是否 为 "admin" 和 "123456"， 若 是则 将 UserMessage 对象中的类型改为"success"，否则将类型改为"fail"并回发给客户端，客户 端接收到服务器发来的对象后判断并给出登录成功或者失败的提示。 

  其中 UserMessage 类的特征有：类型(字符串类型) 和 用户对象(User 类型)。 

  其中 User 类的特征有：用户名、密码(字符串类型)。 

  如： 

                UserMessage tum = new UserMessage("check", new User("admin", "123456"));  

5. 编程题 

  使用基于 tcp 协议的编程模型实现多人同时在线聊天和传输文件，要求每个客户端将发 送的聊天内容和文件发送到服务器，服务器接收到后转发给当前所有在线的客户端。



---------------------------------------------------------------------------------

作业说明：


1、提供编程代码，代码记得要加注释，方便导师能通过浏览代码明白你的程序执行流程；

2、提供程序运行和讲解的视频（程序运行包含：按照作业要求体现程序执行结果；讲解内容包含：题目分析、实现思路、代码讲解。）

3、当前模块学习过程中的笔记，可以以文档、照片、思维导图的形式提供；

4、本次作业的内容，分三个文件夹，code（每个题单独一个文件夹）、运行视频、学习笔记；

5、要求控制台打印出程序的执行流程和最后结果，第二题和第三题不可忽略，将正在复制和正在删除的文件名字打印到控制台中