package cn.lhfei.hbase.ch05.admin;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.hbase.common.HBaseHelper;

/**
 * ListTablesExample Example listing the existing tables and their
 * descriptors
 *
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class ListTablesExample {
	private static final Logger log = LoggerFactory.getLogger(ListTablesExample.class);

	public static void main(String[] args) throws IOException, InterruptedException {
		Configuration conf = HBaseConfiguration.create();

		HBaseHelper helper = HBaseHelper.getHelper(conf);
		helper.dropTable("testtable1");
		helper.dropTable("testtable2");
		helper.dropTable("testtable3");
		helper.createTable("testtable1", "colfam1", "colfam2", "colfam3");
		helper.createTable("testtable2", "colfam1", "colfam2", "colfam3");
		helper.createTable("testtable3", "colfam1", "colfam2", "colfam3");

		
		Connection connection = ConnectionFactory.createConnection(conf);
		Admin admin = connection.getAdmin();

		HTableDescriptor[] htds = admin.listTables();
		log.info("Printing all tables...");
		
		for (HTableDescriptor htd : htds) {
			log.info("{}", htd);
		}

		HTableDescriptor htd1 = admin.getTableDescriptor(TableName.valueOf("testtable1"));
		log.info("Printing testtable1...");
		
		log.info("{}", htd1);

		HTableDescriptor htd2 = admin.getTableDescriptor(TableName.valueOf("testtable10"));
		log.info("Printing testtable10...");
		
		log.info("{}", htd2);
	}
}
