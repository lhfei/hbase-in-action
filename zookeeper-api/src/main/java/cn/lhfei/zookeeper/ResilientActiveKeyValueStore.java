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
package cn.lhfei.zookeeper;

import java.nio.charset.Charset;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since May 3, 2015
 */
public class ResilientActiveKeyValueStore extends ConnectionWatcher {

	private static final Charset CHARSET = Charset.forName("UTF-8");
	private static final int MAX_RETRIES = 5;
	private static final int RETRY_PERIOD_SECONDS = 10;

	public void write(String path, String value) throws InterruptedException,
			KeeperException {
		int retries = 0;
		while (true) {
			try {
				Stat stat = zk.exists(path, false);
				if (stat == null) {
					zk.create(path, value.getBytes(CHARSET),
							Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
				} else {
					zk.setData(path, value.getBytes(CHARSET), stat.getVersion());
				}
				return;
			} catch (KeeperException.SessionExpiredException e) {
				throw e;
			} catch (KeeperException e) {
				if (retries++ == MAX_RETRIES) {
					throw e;
				}
				// sleep then retry
				TimeUnit.SECONDS.sleep(RETRY_PERIOD_SECONDS);
			}
		}
	}

	public String read(String path, Watcher watcher)
			throws InterruptedException, KeeperException {
		byte[] data = zk.getData(path, watcher, null/* stat */);
		return new String(data, CHARSET);
	}
}
