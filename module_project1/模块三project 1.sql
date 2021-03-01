1、在会员分析中计算最近七天连续三天活跃会员数。
with tmp as (
  select device_id, dt, date_sub(dt, row_number() over(partition by device_id order by dt)) gid from dws.dws_member_start_day where dt between  date_sub(current_date, -7) and current_date
)
select count(distinct device_id) total from
(
  select device_id, count(1) cnt from tmp
  group by device_id, gid
  having cnt >= 3
) tmp2;




2、项目的数据采集过程中，有哪些地方能够优化，如何实现？
可以在采集阶段现将无效的日志过滤掉，只保留需要使用的日志数据
其次利用kafka 完成对数据数据缓存，既可以保证数据的不丢失，还可以保证数据的性能不受影响