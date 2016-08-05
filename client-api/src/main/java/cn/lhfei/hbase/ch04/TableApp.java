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
package cn.lhfei.hbase.ch04;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.HBaseAdmin;
import org.apache.hadoop.hbase.client.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.hbase.basic.AppConfig;
import cn.lhfei.hbase.common.HBaseHelper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class TableApp extends AppConfig {
	private static final Logger log = LoggerFactory.getLogger(TableApp.class);
	public static void main(String[] args) throws IOException {
		Configuration configuration = getConfiguration();
		HBaseHelper helper = HBaseHelper.getHelper(configuration);
		Connection connection = helper.getConnection();

		HBaseAdmin admin = (HBaseAdmin)connection.getAdmin();
		
		log.info("Status: {}", admin.getClusterStatus());
		
		Table table = connection.getTable(TableName.valueOf(TEST_TABLE_NAME));
		
		HTableDescriptor desc = table.getTableDescriptor();
		HColumnDescriptor[] cols = desc.getColumnFamilies();
		
		for (HColumnDescriptor col : cols) {

			log.info("ColumnFanily: {}, Blocksize: {}, MaxVersion: {}, Compression: {}, Replication Scope: {}",
					col.getNameAsString(), col.getBlocksize(), col.getMaxVersions(), 
					col.getCompression().getName(),
					col.getScope());
		}
		
		
		
	}

}
