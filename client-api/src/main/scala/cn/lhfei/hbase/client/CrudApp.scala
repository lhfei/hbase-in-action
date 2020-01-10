package cn.lhfei.hbase.client

import org.apache.hadoop.hbase.HBaseConfiguration
import org.apache.hadoop.hbase.client.ConnectionFactory
import scala.util.Properties
import org.apache.hadoop.util.ToolRunner
import org.apache.spark.sql.SparkSession

object CrudApp {
  
    def main(args: Array[String]) {
    	val spark = SparkSession.builder()
    			.appName("Spark Dataframe example by Scala.")
    			.getOrCreate();
      val conf = HBaseConfiguration.create()

      conf.set("hbase.zookeeper.quorum",
        "172.23.226.122,172.23.226.209,172.23.227.70")
      conf.set("zookeeper.znode.parent", "/hbase-unsecure")
    
      val connection = ConnectionFactory.createConnection(conf)
      val admin = connection.getAdmin()

      // list the tables
      for(name <- admin.listTableNames())
        println(name.getNameAsString())

  }
}