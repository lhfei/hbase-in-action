package cn.lhfei.hbase.ch07.mapreduce;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * 
 * BytesSplit Example for splitting a key range into pieces
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class BytesSplit {

  public static void main(String[] args) {
    byte[][] splits = Bytes.split(Bytes.toBytes(0), Bytes.toBytes(100), 9); // co BytesSplit-1-9Splits The number defines the amount of splits performed. Splitting one region nine times results in ten parts.
    int n = 0;
    for (byte[] split : splits) {
      System.out.println("Split key[" + ++n + "]: " +
        Bytes.toInt(split));
    }
  }
}
