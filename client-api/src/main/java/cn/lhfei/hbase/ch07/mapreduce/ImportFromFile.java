package cn.lhfei.hbase.ch07.mapreduce;

import java.io.IOException;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.KeyValue;
import org.apache.hadoop.hbase.client.Mutation;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.mapreduce.TableOutputFormat;
import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.Mapper;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.util.GenericOptionsParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.lhfei.hbase.basic.AppConfig;

/**
 * 
 * ImportFromFile MapReduce job that reads from a file and writes into a table.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class ImportFromFile extends AppConfig{

	private static final Logger log = LoggerFactory.getLogger(ImportFromFile.class);

	public static final String NAME = "ImportFromFile";

	public enum Counters {
		LINES
	}

	/**
	 * Implements the <code>Mapper</code> that takes the lines from the input
	 * and outputs <code>Put</code> instances.
	 */
	static class ImportMapper extends Mapper<LongWritable, Text, ImmutableBytesWritable, Mutation> {

		private byte[] family = null;
		private byte[] qualifier = null;

		/**
		 * Prepares the column family and qualifier.
		 *
		 * @param context
		 *            The task context.
		 * @throws IOException
		 *             When an operation fails - not possible here.
		 * @throws InterruptedException
		 *             When the task is aborted.
		 */
		@Override
		protected void setup(Context context) throws IOException, InterruptedException {
			String column = context.getConfiguration().get("conf.column");
			byte[][] colkey = KeyValue.parseColumn(Bytes.toBytes(column));
			family = colkey[0];
			if (colkey.length > 1) {
				qualifier = colkey[1];
			}
		}

		/**
		 * Maps the input.
		 *
		 * @param offset
		 *            The current offset into the input file.
		 * @param line
		 *            The current line of the file.
		 * @param context
		 *            The task context.
		 * @throws IOException
		 *             When mapping the input fails.
		 */
		@Override
		public void map(LongWritable offset, Text line, Context context) throws IOException {
			try {
				String lineString = line.toString();
				
				log.info("Line: [{}]", lineString);
				
				byte[] rowkey = DigestUtils.md5(lineString);
				Put put = new Put(rowkey);
				put.addColumn(family, qualifier, Bytes.toBytes(lineString));
				context.write(new ImmutableBytesWritable(rowkey), put);
				context.getCounter(Counters.LINES).increment(1);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * Parse the command line parameters.
	 *
	 * @param args
	 *            The parameters to parse.
	 * @return The parsed command line.
	 * @throws ParseException
	 *             When the parsing of the parameters fails.
	 */
	private static CommandLine parseArgs(String[] args) throws ParseException {
		Options options = new Options();
		Option o = new Option("t", "table", true, "table to import into (must exist)");
		o.setArgName("table-name");
		o.setRequired(true);
		options.addOption(o);
		o = new Option("c", "column", true, "column to store row data into (must exist)");
		o.setArgName("family:qualifier");
		o.setRequired(true);
		options.addOption(o);
		o = new Option("i", "input", true, "the directory or file to read from");
		o.setArgName("path-in-HDFS");
		o.setRequired(true);
		options.addOption(o);
		options.addOption("d", "debug", false, "switch on DEBUG log level");
		CommandLineParser parser = new PosixParser();
		CommandLine cmd = null;
		try {
			cmd = parser.parse(options, args);
		} catch (Exception e) {
			System.err.println("ERROR: " + e.getMessage() + "\n");
			HelpFormatter formatter = new HelpFormatter();
			formatter.printHelp(NAME + " ", options, true);
			System.exit(-1);
		}
		return cmd;
	}

	/**
	 * Main entry point.
	 *
	 * @param args
	 *            The command line parameters.
	 * @throws Exception
	 *             When running the job fails.
	 */
	public static void main(String[] args) throws Exception {
		//Configuration conf = HBaseConfiguration.create();
		Configuration conf = AppConfig.getConfiguration();
		String table = "jsontable";
		String input = "/user/lhfei/test-data.txt";
		String column = "data:json";
		
		//args = new String[]{"-tjsontable -isrc/test/resources/test-data.txt -cdata:json"};
		
		if(null != args){
			String[] otherArgs = new GenericOptionsParser(conf, args).getRemainingArgs();
			CommandLine cmd = parseArgs(otherArgs);
			// check debug flag and other options
			if (cmd.hasOption("d"))
				conf.set("conf.debug", "true");
			// get details
			table = cmd.getOptionValue("t");
			input = cmd.getOptionValue("i");
			column = cmd.getOptionValue("c");
		}
		
		conf.set("conf.column", column);

		
		Job job = Job.getInstance(conf, "Import from file " + input + " into table " + table);
		job.setJarByClass(ImportFromFile.class);
		job.setMapperClass(ImportMapper.class);
		job.setOutputFormatClass(TableOutputFormat.class);
		job.getConfiguration().set(TableOutputFormat.OUTPUT_TABLE, table);
		job.setOutputKeyClass(ImmutableBytesWritable.class);
		job.setOutputValueClass(Writable.class);
		job.setNumReduceTasks(0);
		FileInputFormat.addInputPath(job, new Path(input));

		System.exit(job.waitForCompletion(true) ? 0 : 1);
	}
}
