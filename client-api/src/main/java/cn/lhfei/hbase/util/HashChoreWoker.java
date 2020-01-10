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

import java.util.Iterator;
import java.util.TreeSet;

import org.apache.hadoop.hbase.util.Bytes;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @created Dec 25, 2019
 */
public class HashChoreWoker implements SplitKeysCalculator {

	private int baseRecord;
	private RowKeyGenerator rkGen;
	private int splitKeysBase;
	private int splitKeysNumber;
	private byte[][] splitKeys;

	public HashChoreWoker(int baseRecord, int prepareRegions) {
		this.baseRecord = baseRecord;
		rkGen = new HashRowKeyGenerator();
		splitKeysNumber = prepareRegions - 1;
		splitKeysBase = baseRecord / prepareRegions;
	}

	public byte[][] calcSplitKeys() {
		splitKeys = new byte[splitKeysNumber][];
		TreeSet<byte[]> rows = new TreeSet<byte[]>(Bytes.BYTES_COMPARATOR);
		for (int i = 0; i < baseRecord; i++) {
			rows.add(rkGen.nextId());
		}
		int pointer = 1;
		Iterator<byte[]> rowKeyItor = rows.iterator();
		int index = 0;
		while (rowKeyItor.hasNext()) {
			byte[] tempRow = rowKeyItor.next();
			rowKeyItor.remove();
			if ((pointer != 0) && (pointer % splitKeysBase == 0)) {
				if (index < splitKeysNumber) {
					splitKeys[index] = tempRow;
					index++;
				}
			}
			pointer++;
		}
		rows.clear();
		rows = null;
		return splitKeys;
	}

}
