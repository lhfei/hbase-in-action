package cn.lhfei.hbase.ch03;

import java.io.IOException;

import org.apache.hadoop.conf.Configuration;

import cn.lhfei.hbase.basic.AppConfig;
import cn.lhfei.hbase.basic.AppConstant;
import cn.lhfei.hbase.common.HBaseHelper;

public class DataFactory extends AppConfig {

	public static void main(String[] args) throws IOException {
		Configuration conf = getConfiguration();//HBaseConfiguration.create(); // co PutExample-1-CreateConf Create the required configuration.
		
	    HBaseHelper helper = HBaseHelper.getHelper(conf);
	    helper.dropTable(AppConstant.TEST_TABLE_NAME);
	    helper.createTable(AppConstant.TEST_TABLE_NAME, AppConstant.TEST_TABLE_COLUMN_FAMILY_NAME, "colfam2");
	    
	    helper.fillTable(AppConstant.TEST_TABLE_NAME, 1, 100, 100, /* 3, false, */ AppConstant.TEST_TABLE_COLUMN_FAMILY_NAME, "colfam2");
	    
	    helper.close();
	}

}
