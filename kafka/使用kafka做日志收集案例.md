# 使用kafka做日志收集案例

## 需求：使用Kafka做日志收集

**需要收集的信息：**

1、用户ID（user_id）

2、时间（act_time）

3、操作（action，可以是：点击：click，收藏：job_collect，投简历：cv_send，上传简历：cv_upload）

4、对方企业编码（job_code）

**说明：**

1、HTML可以理解为拉勾的职位浏览页面

2、Nginx用于收集用户的点击数据流，记录日志access.log

3、将Nginx收集的日志数据发送到Kafka主题：tp_individual

**架构：**

HTML+Nginx+[ngx_kafka_module](https://github.com/brg-liuwei/ngx_kafka_module)+Kafka

**提示：**

学员需要自己下载nginx，配置nginx的ngx_kafka_module，自定义一个html页面，能做到点击连接就收集用户动作数据即可。

**作业需提交：**

1、html的截图+搭建的过程+结果截图以文档或视频演示形式提供。

2、作业实现过程的代码。

（注意：1、需要把搭建过程用文档写清楚，2、运行效果：在html上点击相应的连接或按钮，应该在Kafka中看到有消息流进来，效果最好以视频形式演示。）





## 操作步骤

### 安装依赖



```shell

# 1.安装git
yum install -y git
# 2.安装相关依赖
yum install -y gcc gcc-c++ zlib zlib-devel openssl openssl-devel pcre pcre-devel
# 3.将kafka的客户端源码clone到本地
cd /opt/lagou/software
git clone https://github.com/edenhill/librdkafka
# 4.编译
./configure
make && make install


# 下载nginx
wget http://nginx.org/download/nginx-1.18.0.tar.gz
# 解压
tar -zxf nginx-1.18.0.tar.gz

# 下载ngx_kafka_module 并编译
cd /opt/lagou/software
git clone https://github.com/brg-liuwei/ngx_kafka_module
# 进入到nginx的源码包目录下   （编译nginx，然后将插件同时编译）
cd /opt/lagou/software/nginx-1.18.0
./configure --prefix=destdir --add-module=/opt/lagou/software/ngx_kafka_module/
make && make install



#创建kafka topic 和创建消费者
kafka-topics.sh --zookeeper localhost:2181/myKafka --create --topic ngx_kafkademo --partitions 3 --replication-factor 2

kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic ngx_kafkademo --from-beginning

```

修改nginx

编辑nginx.conf 

然后启动nginx 

使用curl 发送测试数据

```shell
curl localhost:80/kafka/log -d "test message kafka" -v
```







index.html页面代码，使用`jquery-3.4.1.min.js`使用jQuery实现收集日志的逻辑

```html

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<script src="./jquery-3.4.1.min.js"></script>
<body>
  <input id="user_id" type="text" value="user_001" hidden="hidden">
  <input id="job_code" type="text" value="code001" hidden="hidden">
  <button id="click" class="action" act_type="click">点击</button>
  <button id="collect" class="action" act_type="job_collect">收藏</button>
  <button id="send" class="action" act_type="cv_send">投简历</button>
  <button id="upload" class="action" act_type="cv_upload">上传简历</button>
  <label id="label"></label>
  </body>
  <style>
      .action {
          height: 30px;
          font-family: 微软雅黑;
          font-size: 14px;
          color: aliceblue;
          border: none;
          border-radius: 4px;
      }
 
      #click {
          background-color: cadetblue;
      }
 
      #collect {
          background-color: cornflowerblue;
      }
 
      #send {
          background-color: coral;
      }
 
      #upload {
          background-color: indianred;
      }
  </style>
  <script>
      $(document).ready(function () {
          $(".action").click(function () {
              var user_id = $("#user_id").val();
              var act_time = new Date();
              var action = this.getAttribute("act_type")
              var job_code = $("#job_code").val();
              var log = user_id + "\t" + act_time + "\t" + action + "\t" + job_code;
              $.ajax({
                  url: "http://localhost:81/kafka/log",
                  type: "POST",
                  dataType: "json",
                  data: log
              })
          });
      })
  </script>
</html>
```





