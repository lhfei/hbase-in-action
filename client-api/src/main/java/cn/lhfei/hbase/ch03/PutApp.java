/*
 * Copyright 2010-2014 the original author or authors.
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
package cn.lhfei.hbase.ch03;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;

import cn.lhfei.hbase.basic.AppConfig;
import cn.lhfei.hbase.common.HBaseHelper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since  May 5, 2015
 */
public class PutApp extends AppConfig {

	public static void main(String[] args) throws IOException {
		Configuration conf = getConfiguration();//HBaseConfiguration.create(); // co PutExample-1-CreateConf Create the required configuration.
		
	    HBaseHelper helper = HBaseHelper.getHelper(conf);
	    helper.dropTable("testtable");
	    helper.createTable("testtable", "colfam1");
	    Connection connection = ConnectionFactory.createConnection(conf);
	    Table table = connection.getTable(TableName.valueOf("testtable")); // co PutExample-2-NewTable Instantiate a new client.

	    Put put = new Put(Bytes.toBytes("row1")); // co PutExample-3-NewPut Create put with specific row.

	    put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1"),
	      Bytes.toBytes("val1")); // co PutExample-4-AddCol1 Add a column, whose name is "colfam1:qual1", to the put.
	    put.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual2"),
	      Bytes.toBytes("val2")); // co PutExample-4-AddCol2 Add another column, whose name is "colfam1:qual2", to the put.

	    table.put(put); // co PutExample-5-DoPut Store row with column into the HBase table.
	    table.close(); // co PutExample-6-DoPut Close table and connection instances to free resources.
	    connection.close();
	    helper.close();

	}

}
