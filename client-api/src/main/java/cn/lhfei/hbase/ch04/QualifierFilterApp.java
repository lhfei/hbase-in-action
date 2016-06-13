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
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.filter.BinaryComparator;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.QualifierFilter;
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
public class QualifierFilterApp extends AppConfig {

	private static final Logger log = LoggerFactory.getLogger(QualifierFilterApp.class);

	public static void main(String[] args) throws IOException {
		Configuration configuration = getConfiguration();
		HBaseHelper helper = HBaseHelper.getHelper(configuration);
		Connection connection = helper.getConnection();
		
		/*helper.dropTable(TEST_TABLE_NAME);
	    helper.createTable(TEST_TABLE_NAME, "colfam1", "colfam2");
	    log.info("Adding rows to table...");
	    helper.fillTable("testtable", 1, 10, 10, "colfam1", "colfam2");*/
		
	    Table table = connection.getTable(TableName.valueOf(TEST_TABLE_NAME));
		
	    Filter filter = new QualifierFilter(CompareOp.LESS_OR_EQUAL, new BinaryComparator(Bytes.toBytes("col-2")));
	    
	    Scan scan = new Scan();
	    scan.setFilter(filter);
	    
	    ResultScanner scanner = table.getScanner(scan);
	    log.info("Scanning table... ");
	    for (Result result : scanner) {
	      log.info(result.toString());
	    }
	    scanner.close();

	    Get get1 = new Get(Bytes.toBytes("row-5"));
	    get1.setFilter(filter);
	    Result result1 = table.get(get1);
	    log.info("Result of get(): {}", result1);

	}

}
