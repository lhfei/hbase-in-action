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

import java.io.IOException;
import java.util.concurrent.CountDownLatch;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.Watcher.Event.KeeperState;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.ZooKeeper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since May 3, 2015
 */
public class CreateGroup implements Watcher {
	
	private static final Logger log = LoggerFactory.getLogger(CreateGroup.class);
	private static final int SESSION_TIMEOUT = 5000;
	private ZooKeeper zk;
	private CountDownLatch connectedSignal = new CountDownLatch(1);

	@Override
	public void process(WatchedEvent event) {
		if (event.getState() == KeeperState.SyncConnected) {
			connectedSignal.countDown();
		}
	}
	
	public void connect(String hosts) throws IOException, InterruptedException {
		zk = new ZooKeeper(hosts, SESSION_TIMEOUT, this);
		connectedSignal.await();
	}
	
	public void create(String groupName) throws KeeperException,
			InterruptedException {
		String path = "/" + groupName;
		String createdPath = zk.create(path, "mydata".getBytes()/* data */,
				Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
		log.info("Created {}", createdPath);
	}

	public void close() throws InterruptedException {
		zk.close();
	}

	public static void main(String[] args) throws Exception {
		if(args == null || args.length != 2){
			args = new String[2];
			args[0] = MyZkConfig.ZK_SERVER_MASTER;			// master server
			args[1] = MyZkConfig.ZK_NODE_GROUP_NAME;		// group name
		}
		
		CreateGroup createGroup = new CreateGroup();
		createGroup.connect(args[0]);
		createGroup.create(args[1]);
		createGroup.close();
	}
}
