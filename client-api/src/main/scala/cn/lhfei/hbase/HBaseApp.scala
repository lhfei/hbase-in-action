package cn.lhfei.hbase

import scala.util.Properties

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.TableName
import org.apache.hadoop.hbase.client.ColumnFamilyDescriptorBuilder
import org.apache.hadoop.hbase.client.ConnectionFactory
import org.apache.hadoop.hbase.client.Get
import org.apache.hadoop.hbase.client.Put
import org.apache.hadoop.hbase.client.TableDescriptorBuilder
import org.apache.hadoop.hbase.util.Bytes


object HBaseApp {
  def main(args: Array[String]) {
    val conf = HBaseConfiguration.create()

    println ("hbase.zookeeper.quorum" + (Properties.envOrNone("hbase_zookeeper_quorum")))

    conf.set("hbase.zookeeper.quorum",
        "172.23.226.122,172.23.226.209,172.23.227.70")
    conf.set("zookeeper.znode.parent", "/hbase-unsecure")

    val connection = ConnectionFactory.createConnection(conf)
    
    val admin = connection.getAdmin()

    // list the tables
    for(name <- admin.listTableNames())
      println(name.getNameAsString())

    val tableName = TableName.valueOf("mytable")
    if (admin.tableExists(tableName))
      admin.disableTable(tableName)
    admin.deleteTable(tableName)

    
    val tableDesc = TableDescriptorBuilder.newBuilder(tableName).build()
    val idsColumnFamilyDesc = ColumnFamilyDescriptorBuilder.newBuilder(Bytes.toBytes("ids")).build()
    
    admin.addColumnFamily(tableName, idsColumnFamilyDesc)
    admin.createTable(tableDesc)
    
    // let's insert some data in 'mytable' and get the row
    val table = connection.getTable(tableName)

    val theput= new Put(Bytes.toBytes("rowkey1"))

    theput.addColumn(Bytes.toBytes("ids"),Bytes.toBytes("id1"),Bytes.toBytes("one"))
    
    table.put(theput)

    val theget = new Get(Bytes.toBytes("rowkey1"))
    val result = table.get(theget)
    val value = result.value()
    println(Bytes.toString(value))
  }
}