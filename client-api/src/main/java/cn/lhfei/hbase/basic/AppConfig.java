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

	protected Logger log = LoggerFactory.getLogger(this.getClass());
	
	protected static Configuration getConfiguration() {
		Configuration conf = HBaseConfiguration.create();
		
		conf.set("hbase.master", "114.80.177.136：6000");
		conf.set("hbase.zookeeper.quorum", "114.80.177.136");
		
		return conf;
	}
}
