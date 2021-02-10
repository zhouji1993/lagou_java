package com.lagou.demo.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.CellUtil;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Durability;
import org.apache.hadoop.hbase.client.HTable;
import org.apache.hadoop.hbase.coprocessor.BaseRegionObserver;
import org.apache.hadoop.hbase.coprocessor.ObserverContext;
import org.apache.hadoop.hbase.coprocessor.RegionCoprocessorEnvironment;
import org.apache.hadoop.hbase.regionserver.wal.WALEdit;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.CollectionUtils;

import java.io.IOException;
import java.util.List;
/*
*
*
*
*
* */
public class DeleteUserByHbase extends BaseRegionObserver{

    @Override
    public void postDelete(ObserverContext<RegionCoprocessorEnvironment> e, Delete delete, WALEdit edit, Durability durability) throws IOException {
        super.postDelete(e, delete, edit, durability);

        final HTable user = (HTable) e.getEnvironment().getTable(TableName.valueOf("user"));
        List<Cell> cells= delete.getFamilyCellMap().get(Bytes.toBytes("friend"));
//        先判断返回的结果是不是空对象，如果是直接返回
        if (CollectionUtils.isEmpty(cells)){
            user.close();
            return;
        }

        Cell  cell = cells.get(0);
        Delete otherUserDelete = new Delete(CellUtil.cloneQualifier(cell));
        otherUserDelete.addColumns(Bytes.toBytes("friend"),CellUtil.cloneRow(cell));
        user.delete(otherUserDelete);
        user.close();



    }





}
