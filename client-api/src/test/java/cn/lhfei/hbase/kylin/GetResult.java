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

package cn.lhfei.hbase.kylin;

import cn.lhfei.hbase.basic.AppConfig;
import cn.lhfei.hbase.common.HBaseHelper;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.*;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Sep 1, 2017
 */

public class GetResult extends AppConfig {
	private static final Logger log = LoggerFactory.getLogger(GetResult.class);
	
	private static final String table_name = "KYLIN_2AUZEE5A34";

	private static final String row_key = "\\x00\\x00\\x00\\x00\\x00\\x00\\x00\\x00@S\\x0B6\\x96\\x03\\xE7\\x00\\x02";


	//get "KYLIN_2AUZEE5A34", "\x00\x00\x00\x00\x00\x00\x00\x00@S\x0B6\x96\x03\xE7\x00\x02", {COLUMN => "F2:M"}
	public static void main(String[] args) throws IOException {
		Configuration conf = getConfiguration();

		HBaseHelper helper = HBaseHelper.getHelper(conf);

		Connection connection = helper.getConnection();
		Table table = connection.getTable(TableName.valueOf(table_name));

		/*log.info("======= {} =======", row_key);

		Get get = new Get(new String(row_key).getBytes());
		
		get.addColumn("F2".getBytes(), "M".getBytes());

		Result result = table.get(get);

		byte[] value = result.getValue("F2".getBytes(), "M".getBytes());

		Assert.assertNotNull(value);
		log.info("Column {}:  value: {}", row_key, new String(value));*/

		Filter filter = null;
		Scan scan = new Scan();
		scan.setMaxResultSize(50);

		ResultScanner scanner = table.getScanner(scan);
		int limit = 200;
		int index = 0;
		for(Result result = scanner.next(); result != null && index < limit; result = scanner.next()){

//			log.info("Row   == {} ==", Bytes.toInt(result.getRow()));
			log.info("Row = [{}], Value = [{}]", Bytes.toInt(result.getRow()),
					Bytes.toInt(result.getValue("F1".getBytes(), "M".getBytes())));

			index ++;
		}

		table.close();
		connection.close();
		helper.close();
	}

}
