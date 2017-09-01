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
package cn.lhfei.hbase.basic;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since  May 5, 2015
 */
public abstract class AppConfig {
	protected Logger LOG = LoggerFactory.getLogger(this.getClass());
	
	public static Configuration getConfiguration() {
		Configuration conf = HBaseConfiguration.create();
		
		conf.set("hbase.master", "192.168.118.128：6000");
		conf.set("hbase.zookeeper.quorum", "192.168.118.128");
		//conf.set("zookeeper.znode.parent", "/cloudland");	// Path must start with / character
		conf.set("zookeeper.znode.parent", "/hbase-unsecure");
		return conf;
	}
	
	
	/**
	 * The name of test table.
	 */
	public static final String TEST_TABLE_NAME = "testtable";
	
	/**
	 * The test table column's family name.
	 * 
	 */
	public static final String TEST_TABLE_COLUMN_FAMILY_NAME = "colfam1";
	
	
	public static final String TEST_TABLE_COLUMN_QUALIFIER_NAME = "qual1";
	
	/**
	 * The test table's row name.
	 */
	public static final String TEST_TABLE_ROW_NAME = "row1";
}
