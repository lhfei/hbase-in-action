/*
 * Copyright 2010-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package cn.lhfei.hbase.client;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.MasterNotRunningException;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.ZooKeeperConnectionException;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptor;
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.client.TableDescriptor;
import org.apache.hadoop.hbase.client.TableDescriptorBuilder;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.hbase.util.HashChoreWoker;
import cn.lhfei.hbase.util.HashRowKeyGenerator;
import cn.lhfei.hbase.util.RowKeyGenerator;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Dec 25, 2019
 */
public class HBaseClient {
	private static Logger LOG = LoggerFactory.getLogger(HBaseClient.class);
	private static Configuration config;
	private static Connection conn;

	static {
		config = HBaseConfiguration.create();
		config.set("hbase.zookeeper.quorum", "172.23.226.122,172.23.226.209,172.23.227.70");
		config.set("zookeeper.znode.parent", "/hbase-unsecure");
	}

	/**
	 * @throws Exception
	 */
	public static void testHashAndCreateTable(String tableNameTmp, String columnFamily) throws Exception {
		conn = ConnectionFactory.createConnection(config);
		HashChoreWoker worker = new HashChoreWoker(1000000, 10);
		byte[][] splitKeys = worker.calcSplitKeys();

		Admin admin = conn.getAdmin();
		TableName tableName = TableName.valueOf(tableNameTmp);
		if (admin.tableExists(tableName)) {
			try {
				admin.disableTable(tableName);
			} catch (Exception e) {
				LOG.error(e.getMessage(), e);
			}
			admin.deleteTable(tableName);
		}
		TableDescriptor tableDesc = TableDescriptorBuilder.newBuilder(tableName).build();
		ColumnFamilyDescriptor columnDesc = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes(columnFamily))
				.build();

		admin.addColumnFamily(tableName, columnDesc);

		admin.createTable(tableDesc, splitKeys);
		admin.close();
	}

	/**
	 * @Title: queryData
	 * @param tableName
	 * @param rowkey
	 * @return
	 * @throws Exception
	 */
	public static ArrayList<String> queryData(String tableName, String rowkey) throws Exception {
		conn = ConnectionFactory.createConnection(config);
		ArrayList<String> list = new ArrayList<String>();
		Table table = conn.getTable(TableName.valueOf(tableName));
		Get get = new Get(rowkey.getBytes());
		Result result = table.get(get);

		result.listCells().forEach(cell -> {
			LOG.info("{}: {}", Bytes.toString(cell.getQualifierArray()), Bytes.toString(cell.getValueArray()));
		});
		return list;
	}

	/**
	 * @param tableName
	 * @param rowkey
	 */
	public static void insertData(String tableName, String rowkey) {
		Table table = null;
		try {
			conn = ConnectionFactory.createConnection(config);
			table = conn.getTable(TableName.valueOf(tableName));
			for (int i = 1; i < 100; i++) {
				byte[] result = getNumRowkey(rowkey, i);
				Put put = new Put(result);

				put.addColumn(rowkey.getBytes(), "name".getBytes(), ("aaa" + i).getBytes());
				put.addColumn(rowkey.getBytes(), "age".getBytes(), ("bbb" + i).getBytes());
				put.addColumn(rowkey.getBytes(), "address".getBytes(), ("ccc" + i).getBytes());

				table.put(put);
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}

	public static byte[] getNewRowkey(String rowkey) {
		byte[] result = null;
		RowKeyGenerator rkGen = new HashRowKeyGenerator();
		byte[] splitKeys = rkGen.nextId();
		byte[] rowkeytmp = rowkey.getBytes();
		result = new byte[splitKeys.length + rowkeytmp.length];
		System.arraycopy(splitKeys, 0, result, 0, splitKeys.length);
		System.arraycopy(rowkeytmp, 0, result, splitKeys.length, rowkeytmp.length);
		return result;
	}

	private static byte[] getNumRowkey(String rowkey, int i) {
		byte[] result = null;
		RowKeyGenerator rkGen = new HashRowKeyGenerator();
		byte[] splitKeys = rkGen.nextId();
		byte[] rowkeytmp = rowkey.getBytes();
		byte[] intVal = Bytes.toBytes(i);
		result = new byte[splitKeys.length + rowkeytmp.length + intVal.length];
		System.arraycopy(splitKeys, 0, result, 0, splitKeys.length);
		System.arraycopy(rowkeytmp, 0, result, splitKeys.length, rowkeytmp.length);
		System.arraycopy(intVal, 0, result, splitKeys.length + rowkeytmp.length, intVal.length);
		return result;
	}

	/**
	 * @param tableName
	 */
	public static void dropTable(String tableName) {
		try {
			conn = ConnectionFactory.createConnection(config);
			Admin admin = conn.getAdmin();
			admin.disableTable(TableName.valueOf(tableName));
			admin.deleteTable(TableName.valueOf(tableName));
		} catch (MasterNotRunningException e) {
			e.printStackTrace();
		} catch (ZooKeeperConnectionException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws Exception {
		if (args.length != 2) {
			System.err.println("Wrong number of arguments: " + args.length);
			System.err.println("Usage: <tableName> <rowKey>");
		}
		queryData(args[0], args[1]);
	}
}
