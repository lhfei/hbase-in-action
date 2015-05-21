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
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.hbase.basic.AppConfig;
import cn.lhfei.hbase.basic.AppConstant;
import cn.lhfei.hbase.common.HBaseHelper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since  May 5, 2015
 */
public class DeleteApp extends AppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(DeleteApp.class);

	public static void main(String[] args) throws IOException {
		Configuration configuration = getConfiguration();
		
		HBaseHelper helper = HBaseHelper.getHelper(configuration);
		
		Connection connection = helper.getConnection();
		
	    helper.dropTable(AppConstant.TEST_TABLE_NAME);
	    helper.createTable(AppConstant.TEST_TABLE_NAME, 100, AppConstant.TEST_TABLE_COLUMN_FAMILY_NAME, "colfam2");
	    helper.put(AppConstant.TEST_TABLE_NAME,
	    		new String[] { "row1" },
	    		new String[] { "colfam1", "colfam2" },
	    		new String[] { "qual1", "qual1", "qual2", "qual2", "qual3", "qual3" },
	    		new long[]   { 1, 2, 3, 4, 5, 6 },
	    		new String[] { "val1", "val1", "val2", "val2", "val3", "val3" });
	    
	    log.info("Before delete call...");
	    helper.dump("testtable", new String[]{ "row1" }, null, null);

	    Table table = connection.getTable(TableName.valueOf("testtable"));

	    Delete delete = new Delete(Bytes.toBytes("row1")); 
	    delete.setTimestamp(1);

	    delete.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1")); 
	    delete.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3"), 3); 

	    delete.addColumns(Bytes.toBytes("colfam1"), Bytes.toBytes("qual1")); 
	    delete.addColumns(Bytes.toBytes("colfam1"), Bytes.toBytes("qual3"), 2); 
	    delete.addFamily(Bytes.toBytes("colfam1")); 
	    delete.addFamily(Bytes.toBytes("colfam1"), 3); 

	    table.delete(delete);

	    table.close();
	    connection.close();
	    log.info("After delete call...");
	    helper.dump("testtable", new String[] { "row1" }, null, null);
	    helper.close();
		
		
		
	}
}
