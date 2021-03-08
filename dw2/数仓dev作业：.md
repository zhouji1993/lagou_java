#  作业：
## 描述

	- 1.用拉链表实现核心交易分析中DIM层商家维表，并实现该拉链表的回滚
（自己构造数据，编写SQL，并要有相应的文字说明）
    2. 在会员分析中计算沉默会员数和流失会员数沉默会员的定义：
        只在安装当天启动过App，而且安装时间是在7天前
        流失会员的定义：最近30天未登录的会员
        3. 在核心交易分析中完成如下指标的计算
        统计2020年每个季度的销售订单笔数、订单总额
        统计2020年每个月的销售订单笔数、订单总额
        统计2020年每周（周一到周日）的销售订单笔数、订单总额
        统计2020年国家法定节假日、休息日、工作日的订单笔数、订单总额

## 第一题

1. ### 基础表的metadata

```sql
-- 创建商家信息维表（拉链表）
drop table if exists test.dim_trade_shops;
create table test.dim_trade_shops(
  `shopid` int COMMENT '商铺ID',
  `userid` int COMMENT '商铺联系人ID',
  `areaid` int COMMENT '区域ID',
  `shopname` string COMMENT '商铺名称',
  `shoplevel` int COMMENT '店铺等级',
  `status` int COMMENT '商铺状态',
  `createtime` string COMMENT '创建时间',
  `modifytime` string COMMENT '修改时间',
  `start_dt` string,
  `end_dt` string
) COMMENT '商家信息表';
```
```sql
-- 创建商家信息表（增量表，分区表）
drop table if exists test.ods_trade_shops;
create table test.ods_trade_shops(
  `shopid` int COMMENT '商铺ID',
  `userid` int COMMENT '商铺联系人ID',
  `areaid` int COMMENT '区域ID',
  `shopname` string COMMENT '商铺名称',
  `shoplevel` int COMMENT '店铺等级',
  `status` int COMMENT '商铺状态',
  `createtime` string COMMENT '创建时间',
  `modifytime` string COMMENT '修改时间'
) COMMENT '商家信息表'
PARTITIONED BY (`dt` string)
row format delimited fields terminated by ',';
```

2. ### 测试数据样例
```text
/data/lagoudw/data/shops0712.dat
110050,1,100225,WSxxx营超市,1,1,2020-06-28,2020-07-12 04:22:22     2020-07-12
110051,2,100236,新鲜xxx旗舰店,1,1,2020-06-28,2020-07-12 04:22:22     2020-07-12
110052,3,100011,华为xxx旗舰店,1,1,2020-06-28,2020-07-12 04:22:22     2020-07-12
110053,4,100159,小米xxx旗舰店,1,1,2020-06-28,2020-07-12 04:22:22     2020-07-12

/data/lagoudw/data/shops0713.dat
110052,3,100010,华为xxx旗舰店,2,1,2020-06-28,2020-07-13 04:22:22     2020-07-13
110054,6,100050,OPxxx自营店,1,1,2020-06-28,2020-07-13 04:22:22     2020-07-13
110055,7,100311,三只xxx鼠零食,1,1,2020-06-28,2020-07-13 04:22:22     2020-07-13

/data/lagoudw/data/shops0714.dat
110050,1,100225,WSxxx营超市,1,2,2020-06-28,2020-07-14 04:22:22     2020-07-14
110054,6,100050,OPxxx自营店,2,1,2020-06-28,2020-07-14 04:22:22     2020-07-14
110056,9,100225,乐居xxx日用品,1,1,2020-06-28,2020-07-14 04:22:22     2020-07-14
110057,10,100211,同仁xxx大健康,1,1,2020-06-28,2020-07-14 04:22:22     2020-07-14
```


3. ### 使用load 命令完成对数据的加载
```shell
load data local inpath '/data/lagoudw/data/shops0712.dat' overwrite into table test.ods_trade_shops partition(dt='2020-07-12');
```

4. ### 初始化拉链表数据2020-07-12
```sql
insert overwrite table test.dim_trade_shops
SELECT shopid,userid,areaid,shopname,shoplevel,STATUS,createtime,
modifytime,CASE WHEN modifytime IS NOT NULL THEN substr(modifytime, 0, 10) ELSE substr(createtime, 0, 10) END AS start_dt,'9999-12-31' AS end_dt 
FROM test.ods_trade_shops
WHERE dt='2020-07-12';
```

5. ### 加载拉链表数据2020-07-13
```sql
-- 加载2020-07-13的数据到表
load data local inpath '/data/lagoudw/data/shops0713.dat' overwrite into table test.ods_trade_shops partition(dt='2020-07-13');
```
6. ### 导入2020-07-13的数据
```sql
-- 导入2020-07-13的数据
insert overwrite table test.dim_trade_shops
-- 新增的数据
SELECT
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime,
  CASE WHEN modifytime IS NOT NULL THEN substr(modifytime, 0, 10)
  ELSE substr(createtime, 0, 10) END AS start_dt,
  '9999-12-31' AS end_dt 
FROM test.ods_trade_shops WHERE dt='2020-07-13'
union all
-- 历史数据，未变化的不用处理；变化的数据将结束日期-1
SELECT
  B.shopid,
  B.userid,
  B.areaid,
  B.shopname,
  B.shoplevel,
  B.STATUS,
  B.createtime,
  B.modifytime,
  B.start_dt,
  CASE WHEN A.shopid IS NOT NULL and B.end_dt='9999-12-31' THEN date_add('2020-07-13', -1) ELSE B.end_dt END AS end_dt 
FROM (select * from test.ods_trade_shops where dt='2020-07-13') A
right join test.dim_trade_shops B ON A.shopid=B.shopid;
```
7. ### 加载拉链表数据2020-07-14
```sql
-- 加载2020-07-14的数据到表
load data local inpath '/data/lagoudw/data/shops0714.dat' overwrite into table test.ods_trade_shops partition(dt='2020-07-14');
`
-- 导入2020-07-14的数据
insert overwrite table test.dim_trade_shops
-- 新增的数据
SELECT
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime,
  CASE WHEN modifytime IS NOT NULL THEN substr(modifytime, 0, 10)
  ELSE substr(createtime, 0, 10) END AS start_dt,
  '9999-12-31' AS end_dt 
FROM test.ods_trade_shops WHERE dt='2020-07-14'
union all
-- 历史数据，未变化的不用处理；变化的数据将结束日期-1
SELECT
  B.shopid,
  B.userid,
  B.areaid,
  B.shopname,
  B.shoplevel,
  B.STATUS,
  B.createtime,
  B.modifytime,
  B.start_dt,
  CASE WHEN A.shopid IS NOT NULL and B.end_dt='9999-12-31' THEN date_add('2020-07-14', -1) ELSE B.end_dt END AS end_dt 
FROM (select * from test.ods_trade_shops where dt='2020-07-14') A
right join test.dim_trade_shops B ON A.shopid=B.shopid;
```


8. ### 拉链表使用的计算脚本
```shell
#! /bin/bash
source /etc/profile
if [ -n "$1" ]
then
    do_date=$1
else
    do_date=`date -d "-1 day" +%F`
fi
sql="
insert overwrite table dim.dim_trade_shops
-- 新增的数据
SELECT
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime,
  CASE WHEN modifytime IS NOT NULL THEN substr(modifytime, 0, 10)
  ELSE substr(createtime, 0, 10) END AS start_dt,
  '9999-12-31' AS end_dt 
FROM ods.ods_trade_shops WHERE dt='$do_date'
union all
-- 历史数据，未变化的不用处理；变化的数据将结束日期-1
SELECT
  B.shopid,
  B.userid,
  B.areaid,
  B.shopname,
  B.shoplevel,
  B.STATUS,
  B.createtime,
  B.modifytime,
  B.start_dt,
  CASE WHEN A.shopid IS NOT NULL THEN date_add('$do_date', -1) ELSE B.end_dt END AS end_dt 
FROM (select * from ods.ods_trade_shops where dt='$do_date') A
right join dim.dim_trade_shops B ON A.shopid=B.shopid;
"
hive -e "$sql"
```

### 拉链表回滚实现

`拉链表数据的回滚分析`

`end_date < rollback_date，即结束日期 < 回滚日期。表示该行数据在 rollback_date 之前产生，这些数据需要原样保留`

`start_date <= rollback_date <= end_date，即开始日期 <= 回滚日期 <= 结束日期。这些数据是回滚日期之后产生的，但是需要修改。将end_date 改为 9999-12-31`

`其他数据不用管`

`先将回滚后的数据存入临时表，以回滚到2020-07-13日的数据为例`

```sql
drop table if exists test.shops_tmp;
create table if not exists test.shops_tmp as
select
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime, start_dt, end_dt
from test.dim_trade_shops
where end_dt < '2020-07-13'
union all
select
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime, start_dt, '9999-12-31' as end_date
from test.dim_trade_shops
where start_dt <= '2020-07-13' and end_dt >= '2020-07-13';

-- 将临时表中的数据插入商家维表,回滚完成
insert overwrite table test.dim_trade_shops select * from test.shops_tmp;
```



回滚脚本内容

```shell
#!/bin/bash
source /etc/profile
if [ -n "$1" ]
then
    do_date=$1
else
    do_date=`date -d "-1 day" +%F`
fi
sql="
drop table if exists test.shops_tmp;
create table if not exists test.shops_tmp as
select
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime, start_dt, end_dt
from test.dim_trade_shops
where end_dt < '$do_date'
union all
select
  shopid,
  userid,
  areaid,
  shopname,
  shoplevel,
  STATUS,
  createtime,
  modifytime, start_dt, '9999-12-31' as end_dt
from test.dim_trade_shops
where start_dt <= '$do_date' and end_dt >= '$do_date';

-- 将临时表中的数据插入商家维表,回滚完成
insert overwrite table test.dim_trade_shops select * from test.shops_tmp;
"
hive -e "$sql"
```

## 会员分析中计算沉默会员和流失会员数

1. ### 沉默会员

   需求分析

   沉默会员：7天前安装的且只在安装当天启动过，即7天前启动过且只启动过一次。

   ```sql
   -- 获取只启动过一次的会员
   select device_id, count(*) cnt from dws.dws_member_start_day group by device_id having cnt = 1;
   
   -- 从只启动过一次的会员中取七天前启动的会员即为沉默会员,统计得到沉默会员数clientNum
   select count(*) clientNum from dws.dws_member_start_day where dt <= date_add(current_date, -7) and device_id in (
   select device_id from (select device_id, count(*) cnt from dws.dws_member_start_day group by device_id having cnt = 1) tmp
   );
   ```

​		


2. ### 流失会员

需求分析：

​		根据定义可知，在所有会员中排除最近30天内登陆过的会员，即为流失会员。

​		

```
-- 获取最近30天内登陆过的会员
select distinct device_id from dws.dws_member_start_day where dt >= date_add(current_date, -30);

-- 排除最近30天内登陆过的会员，即为流失会员，统计得到流失会员数lossNum
select count(distinct device_id) lossNum from dws.dws_member_start_day where device_id not in (
  select distinct device_id from dws.dws_member_start_day where dt >= date_add(current_date, -30)
);
--此处需要设置hive属性 set hive.strict.checks.cartesian.product=false;方可执行
```



## 销售指标统计

先统计每天的销售订单笔数和每日订单总额

'订单状态 -3:用户拒收 -2:未付款的订单 -1:用户取消 0:待发货 1:配送中 2:用户确认收货'

'订单有效标志 -1:删除 1:有效'

分析：

- 一个订单可能会有多个状态即多条数据，但是只能统计为1笔。
- 只统计有效的订单且订单状态为0，1，2的订单。

统计2020年每个季度的销售订单笔数、订单总额

统计2020年每个月的销售订单笔数、订单总额

统计2020年每周（周一到周日）的销售订单笔数、订单总额

统计2020年国家法定节假日、休息日、工作日的订单笔数、订单总额

元数据初始化

```sql
-- 1.创建日统计表
drop table if exists dws.dws_trade_orders_day;
create table if not exists dws.dws_trade_orders_day(
  day_dt string comment '日期:yyyy-MM-dd',
  day_cnt decimal comment '日订单笔数',
  day_sum decimal comment '日订单总额'
) comment '日订单统计表';

-- 2.根据订单事实表统计每天的订单笔数和订单总额
select dt, count(*) cnt, sum(totalMoney) sm from (select distinct orderid, dt, totalMoney from dwd.dwd_trade_orders where status >= 0 and dataFlag = '1') tmp group by dt;

-- 3.将统计结果插入日订单统计表中
insert overwrite table dws.dws_trade_orders_day
select dt, count(*) cnt, sum(totalMoney) sm from (select distinct orderid, dt, totalMoney from dwd.dwd_trade_orders where status >= 0 and dataFlag = '1') tmp group by dt;

-- 4.2020年每日订单笔数和订单总额
select * from dws.dws_trade_orders_day where day_dt between '2020-01-01' and '2020-12-31';
```

### 1、统计2020年每个季度的销售订单笔数、订单总额

根据每日的统计结果，计算每个季度的统计结果

```sql
-- 创建季度统计表
drop table if exists dws.dws_trade_orders_quarter;
create table if not exists dws.dws_trade_orders_quarter(
  trade_year string comment '年份',
  quarter string comment '季度',
  quarter_cnt decimal comment '季度订单总笔数',
  quarter_sum decimal comment '季度订单总额'
) comment '季度订单统计表';

-- 将季度订单统计数据插入季度统计表
insert overwrite table dws.dws_trade_orders_quarter
-- 根据日订单统计表计算季度订单
with tmp as (
  select substr(day_dt, 0, 4) trade_year, substr(day_dt, 6) month_day, day_dt, day_cnt, day_sum from dws.dws_trade_orders_day
)
-- 第一季度
select trade_year, '1' as quarter, sum(day_cnt) quarter_cnt, sum(day_sum) quarter_sum from tmp where month_day between '01-01' and '03-31'
group by trade_year
union all
-- 第二季度
select trade_year, '2' as quarter, sum(day_cnt) quarter_cnt, sum(day_sum) quarter_sum from tmp where month_day between '04-01' and '06-30'
group by trade_year
union all
-- 第三季度
select trade_year, '3' as quarter, sum(day_cnt) quarter_cnt, sum(day_sum) quarter_sum from tmp where month_day between '07-01' and '09-30'
group by trade_year
union all
-- 第四季度
select trade_year, '4' as quarter, sum(day_cnt) quarter_cnt, sum(day_sum) quarter_sum from tmp where month_day between '10-01' and '12-31'
group by trade_year;

-- 2020年每个季度的销售订单笔数和订单总额
select * from dws.dws_trade_orders_quarter where trade_year = '2020';
```





## 2、统计2020年每个月的销售订单笔数、订单总额

根据每日订单统计数据，计算每月订单统计数据。

```sql
-- 创建月订单统计表
drop table if exists dws.dws_trade_orders_month;
create table if not exists dws.dws_trade_orders_month(
  trade_year string comment '年份',
  trade_month string comment '月份',
  month_cnt decimal comment '月订单总笔数',
  month_sum decimal comment '月订单总额'
) comment '月订单统计表';

-- 将月订单统计数据插入月订单统计表
insert overwrite table dws.dws_trade_orders_month
-- 根据日订单统计表计算月订单
with tmp as(
    select substr(day_dt, 0, 4) trade_year, substr(day_dt, 6, 2) trade_month, day_dt, day_cnt, day_sum from dws.dws_trade_orders_day
)
select trade_year, trade_month, sum(day_cnt) month_cnt, sum(day_sum) month_sum from tmp group by trade_year, trade_month;

-- 方法二
insert overwrite table dws.dws_trade_orders_month
select substr(day_dt, 0, 4), substr(day_dt, 6, 2), sum(day_cnt) month_cnt, sum(day_sum) month_sum 
from dws.dws_trade_orders_day 
group by substr(day_dt, 0, 4), substr(day_dt, 6, 2);

-- 2020年每月的销售订单笔数和订单总额
select * from dws.dws_trade_orders_month where trade_year = '2020';
```

扩展：上一题可以根据每月订单统计信息计算每个季度订单统计信息：

```sql
insert overwrite table dws.dws_trade_orders_quarter
-- 第一季度
select trade_year, '1' as quarter, sum(month_cnt) quarter_cnt, sum(month_sum) quarter_sum 
from dws.dws_trade_orders_month where trade_month between '01' and '03'
group by trade_year
union all
-- 第二季度
select trade_year, '2' as quarter, sum(month_cnt) quarter_cnt, sum(month_sum) quarter_sum 
from dws.dws_trade_orders_month where trade_month between '04' and '06'
group by trade_year
union all
-- 第三季度
select trade_year, '3' as quarter, sum(month_cnt) quarter_cnt, sum(month_sum) quarter_sum 
from dws.dws_trade_orders_month where trade_month between '07' and '09'
group by trade_year
union all
-- 第四季度
select trade_year, '4' as quarter, sum(month_cnt) quarter_cnt, sum(month_sum) quarter_sum 
from dws.dws_trade_orders_month where trade_month between '10' and '12'
group by trade_year;
```

## 3、统计2020年每周（周一到周日）的销售订单笔数、订单总额

该问题的关键在于如何根据日期得到第几周，第几周的计算可以通过自定义函数实现。

（1） 编写Java代码，实现传入一个日期返回该日期属于第几周

① 创建maven工程，添加依赖

```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.lagou</groupId>
    <artifactId>udf-weekofyear</artifactId>
    <version>1.0-SNAPSHOT</version>

    <dependencies>
        <dependency>
            <groupId>org.apache.hive</groupId>
            <artifactId>hive-exec</artifactId>
            <version>2.3.7</version>
        </dependency>
    </dependencies>
</project>
```

② 继承UDF，实现自定义函数

*com.hive.udf.WeekOfYear*

```java
package com.hive.udf;

import org.apache.hadoop.hive.ql.exec.UDF;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class WeekOfYear extends UDF {
    public IntWritable evaluate(final Text datestr) {
        String today = datestr.toString();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(today);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setFirstDayOfWeek(Calendar.MONDAY);
        calendar.setTime(date);

        int i = calendar.get(Calendar.WEEK_OF_YEAR);
        return new IntWritable(i);
    }
}
```

③ 测试自定义函数

（2） 将项目打包上传至服务器

（3） 添加jar包到hive，创建临时函数，即可使用

```sql
add jar /data/lagoudw/jars/udf-weekofyear-1.0-SNAPSHOT.jar;
create temporary function weekofyear as "com.hive.udf.WeekOfYear";
```

（4） 统计2020年每周（周一到周日）的销售订单笔数、订单总额

```sql
-- 创建周订单统计表
drop table if exists dws.dws_trade_orders_week;
create table if not exists dws.dws_trade_orders_week(
  trade_year string comment '年份',
  trade_week string comment '一年中的第几周',
  week_cnt decimal comment '周订单总笔数',
  week_sum decimal comment '周订单总额'
) comment '周订单统计表';

-- 统计周销售信息插入周订单统计表
insert overwrite table dws.dws_trade_orders_week
select substr(day_dt, 0, 4) trade_year, weekofyear(day_dt) trade_week, sum(day_cnt), sum(day_sum) 
from dws.dws_trade_orders_day group by substr(day_dt, 0, 4), weekofyear(day_dt);

--2020年每周销售订单笔数和订单总额
select * from dws.dws_trade_orders_week where trade_year = '2020';
```

## 4、统计2020年国家法定节假日、休息日、工作日的订单笔数、订单总额

分析：要统计法定节假日、休息日、工作日的销售信息，则必须先得到一年中哪些是节假日、哪些是休息日、哪些是工作日，而这些信息每年都是不一样的并不是固定不变的。所以我们需要建立一张维表来维护这些信息，并且只需要每年初始化一次该维表就可以了（或者针对每年都建立一张维表并初始化一次即可）。

① 创建日期信息维表

```sql
drop table if exists dim.dim_day_info;
create table if not exists dim.dim_day_info(
  day_dt string comment '日期',
  is_holidays string comment '节假日标识：0：非节假日，1：节假日',
  is_workday decimal comment '工作日标识：0：非工作日，1：工作日'
) comment '日期信息表';
```

② 初始化日期信息维表

③ 根据日期信息维表和日订单统计表统计

```sql
-- 统计2020年节假日的订单笔数、订单总额
select nvl(sum(day_cnt), 0), nvl(sum(day_sum), 0) from dws.dws_trade_orders_day A left join dim.dim_day_info B on A.day_dt = B.day_dt where B.is_holidays = '1';

-- 统计2020年休息日的订单笔数、订单总额
select nvl(sum(day_cnt), 0), nvl(sum(day_sum), 0) from dws.dws_trade_orders_day A left join dim.dim_day_info B on A.day_dt = B.day_dt where B.is_workday = '0';

-- 统计2020年工作日的订单笔数、订单总额
select nvl(sum(day_cnt), 0), nvl(sum(day_sum), 0) from dws.dws_trade_orders_day A left join dim.dim_day_info B on A.day_dt = B.day_dt where B.is_workday = '1';
```