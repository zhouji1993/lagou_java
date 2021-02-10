#!/bin/sh

echo 'analysis user click。。。'

currDate=`date +%Y-%m-%d`

echo "现在时间：'$currDate'"

hive -e "USE default;INSERT INTO TABLE user_info SELECT COUNT(DISTINCT id) active_num, TO_DATE(click_time) `date` FROM user_clicks WHERE TO_DATE(click_time) = '$currDate' GROUP BY TO_DATE(click_time);"