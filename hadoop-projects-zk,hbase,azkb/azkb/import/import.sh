#!/bin/sh

echo 'import data from hdfs。。。'

currDate=`date +%Y%m%d`

echo "现在时间：'$currDate'"

hive -e "USE default;LOAD DATA INPATH '/user_clicks/$currDate/*' OVERWRITE INTO TABLE user_clicks PARTITION (dt='$currDate');"

