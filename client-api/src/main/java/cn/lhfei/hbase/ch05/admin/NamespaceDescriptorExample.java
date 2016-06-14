package cn.lhfei.hbase.ch05.admin;

import java.io.IOException;

import org.apache.hadoop.hbase.NamespaceDescriptor;

/**
 * NamespaceDescriptorExample Example how to create a NamespaceDescriptor
 * in code
 *
 * 
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since Jun 13, 2016
 */
public class NamespaceDescriptorExample {

  public static void main(String[] args) throws IOException, InterruptedException {
    // vv NamespaceDescriptorExample
    NamespaceDescriptor.Builder builder =
      NamespaceDescriptor.create("testspace");
    builder.addConfiguration("key1", "value1");
    NamespaceDescriptor desc = builder.build();
    System.out.println("Namespace: " + desc);
    // ^^ NamespaceDescriptorExample
  }
}
