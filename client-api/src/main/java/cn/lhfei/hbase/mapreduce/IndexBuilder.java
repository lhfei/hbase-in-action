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

package cn.lhfei.hbase.mapreduce;

import java.io.IOException;
import java.util.TreeMap;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.conf.Configured;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.MultiTableOutputFormat;
import org.apache.hadoop.hbase.mapreduce.TableInputFormat;
import org.apache.hadoop.hbase.mapreduce.TableMapReduceUtil;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.util.Tool;
import org.apache.hadoop.util.ToolRunner;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Dec 20, 2019
 */

public class IndexBuilder extends Configured implements Tool {

	/** the column family containing the indexed row key */
	public static final byte[] INDEX_COLUMN = Bytes.toBytes("INDEX");
	/** the qualifier containing the indexed row key */
	public static final byte[] INDEX_QUALIFIER = Bytes.toBytes("ROW");

	/**
	 * Internal Mapper to be run by Hadoop.
	 */
	public static class Map extends Mapper<ImmutableBytesWritable, Result, ImmutableBytesWritable, Put> {
		private byte[] family;
		private TreeMap<byte[], ImmutableBytesWritable> indexes;

		@Override
		protected void map(ImmutableBytesWritable rowKey, Result result, Context context)
				throws IOException, InterruptedException {
			for (java.util.Map.Entry<byte[], ImmutableBytesWritable> index : indexes.entrySet()) {
				byte[] qualifier = index.getKey();
				ImmutableBytesWritable tableName = index.getValue();
				byte[] value = result.getValue(family, qualifier);
				if (value != null) {
					// original: row 123 attribute:phone 555-1212
					// index: row 555-1212 INDEX:ROW 123
					Put put = new Put(value);
					put.addColumn(INDEX_COLUMN, INDEX_QUALIFIER, rowKey.get());
					context.write(tableName, put);
				}
			}
		}

		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			Configuration configuration = context.getConfiguration();
			String tableName = configuration.get("index.tablename");
			String[] fields = configuration.getStrings("index.fields");
			String familyName = configuration.get("index.familyname");
			family = Bytes.toBytes(familyName);
			indexes = new TreeMap<>(Bytes.BYTES_COMPARATOR);
			for (String field : fields) {
				// if the table is "people" and the field to index is "email", then the
				// index table will be called "people-email"
				indexes.put(Bytes.toBytes(field), new ImmutableBytesWritable(Bytes.toBytes(tableName + "-" + field)));
			}
		}
	}

	/**
	 * Job configuration.
	 */
	public static Job configureJob(Configuration conf, String[] args) throws IOException {
		String tableName = args[0];
		String columnFamily = args[1];
		System.out.println("****" + tableName);
		conf.set(TableInputFormat.SCAN, TableMapReduceUtil.convertScanToString(new Scan()));
		conf.set(TableInputFormat.INPUT_TABLE, tableName);
		conf.set("index.tablename", tableName);
		conf.set("index.familyname", columnFamily);
		String[] fields = new String[args.length - 2];
		System.arraycopy(args, 2, fields, 0, fields.length);
		conf.setStrings("index.fields", fields);
		Job job = Job.getInstance(conf, tableName);
		job.setJarByClass(IndexBuilder.class);
		job.setMapperClass(Map.class);
		job.setNumReduceTasks(0);
		job.setInputFormatClass(TableInputFormat.class);
		job.setOutputFormatClass(MultiTableOutputFormat.class);
		return job;
	}

	@Override
	public int run(String[] args) throws Exception {
		Configuration conf = HBaseConfiguration.create(getConf());
		if (args.length < 3) {
			System.err.println("Only " + args.length + " arguments supplied, required: 3");
			System.err.println("Usage: IndexBuilder <TABLE_NAME> <COLUMN_FAMILY> <ATTR> [<ATTR> ...]");
			System.exit(-1);
		}
		Job job = configureJob(conf, args);
		return (job.waitForCompletion(true) ? 0 : 1);
	}

	public static void main(String[] args) throws Exception {
		int result = ToolRunner.run(HBaseConfiguration.create(), new IndexBuilder(), args);
		System.exit(result);
	}

}
