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

package cn.lhfei.hbase.util;

import java.util.Random;

import org.apache.hadoop.hbase.util.Bytes;
import org.apache.hadoop.hbase.util.MD5Hash;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Dec 25, 2019
 */
public class HashRowKeyGenerator implements RowKeyGenerator {

	private long currentId = 1;
	private long currentTime = System.currentTimeMillis();
	private Random random = new Random();

	public byte[] nextId() {
		try {
			currentTime += random.nextInt(1000);
			byte[] lowT = Bytes.copy(Bytes.toBytes(currentTime), 4, 4);
			byte[] lowU = Bytes.copy(Bytes.toBytes(currentId), 4, 4);
			return Bytes.add(MD5Hash.getMD5AsHex(Bytes.add(lowU, lowT)).substring(0, 8).getBytes(),
					Bytes.toBytes(currentId));
		} finally {
			currentId++;
		}
	}

	public static byte[] getNewRowkey(String rowkey) {
		byte[] result = null;
		RowKeyGenerator rkGen = new HashRowKeyGenerator();
		byte[] splitKeys = rkGen.nextId();
		byte[] rowkeytmp = rowkey.getBytes();
		result = new byte[splitKeys.length + rowkeytmp.length];
		System.arraycopy(splitKeys, 0, result, 0, splitKeys.length);
		System.arraycopy(rowkeytmp, 0, result, splitKeys.length, rowkeytmp.length);
		return result;
	}

	public static byte[] getNumRowkey(String rowkey, int i) {
		byte[] result = null;
		RowKeyGenerator rkGen = new HashRowKeyGenerator();
		byte[] splitKeys = rkGen.nextId();
		byte[] rowkeytmp = rowkey.getBytes();
		byte[] intVal = Bytes.toBytes(i);
		result = new byte[splitKeys.length + rowkeytmp.length + intVal.length];
		System.arraycopy(splitKeys, 0, result, 0, splitKeys.length);
		System.arraycopy(rowkeytmp, 0, result, splitKeys.length, rowkeytmp.length);
		System.arraycopy(intVal, 0, result, splitKeys.length + rowkeytmp.length, intVal.length);
		return result;
	}
}
