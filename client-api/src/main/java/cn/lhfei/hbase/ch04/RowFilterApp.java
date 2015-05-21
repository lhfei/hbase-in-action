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
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.filter.SubstringComparator;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.hbase.basic.AppConfig;
import cn.lhfei.hbase.common.HBaseHelper;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since  May 6, 2015
 */
public class RowFilterApp extends AppConfig {
	
	private static final Logger log = LoggerFactory.getLogger(RowFilterApp.class);

	public static void main(String[] args) throws IOException {
		Configuration configuration = getConfiguration();
		HBaseHelper helper = HBaseHelper.getHelper(configuration);
		
		Connection connection = helper.getConnection();
		Table table = connection.getTable(TableName.valueOf(TEST_TABLE_NAME));
		
		helper.dropTable(TEST_TABLE_NAME);
		helper.createTable(TEST_TABLE_NAME, "colfam1", "colfam2");
		helper.fillTable(TEST_TABLE_NAME, 1, 100, 100, "colfam1", "colfam2");
		
		Scan scan = new Scan();
		scan.addColumn(Bytes.toBytes("colfam1"), Bytes.toBytes("col-1"));
		
		Filter filter = new RowFilter(CompareFilter.CompareOp.LESS_OR_EQUAL, 
				new BinaryComparator(Bytes.toBytes("row-22")));
		scan.setFilter(filter);
		
		ResultScanner scanner1 = table.getScanner(scan);
		log.info("Scanning table #1...");
		for(Result result : scanner1){
			log.info(result.toString());
		}
		scanner1.close();
		
		Filter filter2 = new RowFilter(CompareFilter.CompareOp.EQUAL,
				new RegexStringComparator(".*-.5"));
		scan.setFilter(filter2);
		
		ResultScanner scanner2 = table.getScanner(scan);
		log.info("Scanning table #2...");
		for (Result res : scanner2) {
			log.info(res.toString());
		}
		scanner2.close();

		Filter filter3 = new RowFilter(CompareFilter.CompareOp.EQUAL,
				new SubstringComparator("-5"));
		scan.setFilter(filter3);
		
		ResultScanner scanner3 = table.getScanner(scan);
		log.info("Scanning table #3...");
		for (Result res : scanner3) {
			log.info(res.toString());
		}
		scanner3.close();
		
	}

}
