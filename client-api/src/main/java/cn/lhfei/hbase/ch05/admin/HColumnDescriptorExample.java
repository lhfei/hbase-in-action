package cn.lhfei.hbase.ch05.admin;

import java.io.IOException;
import java.util.Map;

import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.io.ImmutableBytesWritable;
import org.apache.hadoop.hbase.regionserver.BloomType;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * HColumnDescriptorExample Example how to create a HColumnDescriptor in code.
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class HColumnDescriptorExample {

	private static final Logger log = LoggerFactory.getLogger(HColumnDescriptorExample.class);

	public static void main(String[] args) throws IOException, InterruptedException {
		HColumnDescriptor desc = new HColumnDescriptor("colfam1").setValue("test-key", "test-value")
				.setBloomFilterType(BloomType.ROWCOL);

		log.info("Column Descriptor: " + desc);

		for (Map.Entry<ImmutableBytesWritable, ImmutableBytesWritable> entry : desc.getValues().entrySet()) {
			log.info(Bytes.toString(entry.getKey().get()) + " -> " + Bytes.toString(entry.getValue().get()) + ", ");
		}

		log.info("Defaults: " + HColumnDescriptor.getDefaultValues());

		log.info("Custom: " + desc.toStringCustomizedValues());

		log.info("Units:");
		log.info(HColumnDescriptor.TTL + " -> " + desc.getUnit(HColumnDescriptor.TTL));
		log.info(HColumnDescriptor.BLOCKSIZE + " -> " + desc.getUnit(HColumnDescriptor.BLOCKSIZE));
	}
}
