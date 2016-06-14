package cn.lhfei.hbase.ch07.mapreduce;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.io.Writable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class InvalidReducerOverride {

  static class InvalidOverrideReduce
  extends Reducer<Writable, Writable, Writable, Writable> {
    //@Override
    protected void reduce(Writable key, Iterator values, Context context)
      throws IOException, InterruptedException {
      context.write(key, new Text());
    }
  }

}
