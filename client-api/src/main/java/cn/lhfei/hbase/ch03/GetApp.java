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
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
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
public class GetApp extends AppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(GetApp.class);

	public static void main(String[] args) throws IOException {
		Configuration conf = getConfiguration();
		
		HBaseHelper helper = HBaseHelper.getHelper(conf);
		
		if(!helper.existsTable(AppConstant.TEST_TABLE_NAME)){
			helper.createTable(AppConstant.TEST_TABLE_NAME, AppConstant.TEST_TABLE_COLUMN_FAMILY_NAME);
		}
		
		Connection connection = helper.getConnection();
		Table table = connection.getTable(TableName.valueOf(AppConstant.TEST_TABLE_NAME));
		
		Get get = new Get(AppConstant.TEST_TABLE_ROW_NAME.getBytes());
		get.addColumn(AppConstant.TEST_TABLE_COLUMN_FAMILY_NAME.getBytes(),
				AppConstant.TEST_TABLE_COLUMN_QUALIFIER_NAME.getBytes());
		
		
		Result result = table.get(get);
		
		
		byte[] value = result.getValue(AppConstant.TEST_TABLE_COLUMN_FAMILY_NAME.getBytes(),
				AppConstant.TEST_TABLE_COLUMN_QUALIFIER_NAME.getBytes()); 
		
		log.info("Column {}:  value: {}", AppConstant.TEST_TABLE_ROW_NAME, new String(value));
		
		table.close();
		connection.close();
		helper.close();
	}

}
