package com.suda.cs;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;

import java.io.IOException;


/**
 * Created by fei on 2016/6/1.
 */
public class HbaseOperate {
    public static void createTable(Table table){
        Configuration conf = HBaseConfiguration.create();
        try {
            HBaseAdmin admin = new HBaseAdmin(conf);
            if (admin.tableExists(table.getName())) {// 如果存在要创建的表，那么先删除，再创建
                admin.disableTable(table.getName());
                admin.deleteTable(table.getName());
            }
            HTableDescriptor tableDescriptor = new HTableDescriptor(TableName.valueOf(table.getName()));
            for (Family family : table.getFamilies()) {
                tableDescriptor.addFamily(new HColumnDescriptor(family.getName()));
            }
            admin.createTable(tableDescriptor);
            boolean tableAvailable = admin.isTableAvailable(table.getName());
            System.out.println("tableAvailable = " + tableAvailable);
        }catch (MasterNotRunningException e) {
            e.printStackTrace();
        } catch (ZooKeeperConnectionException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
