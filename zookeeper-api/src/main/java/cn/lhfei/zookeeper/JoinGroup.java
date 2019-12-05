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

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.ZooDefs.Ids;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since May 3, 2015
 */
public class JoinGroup extends ConnectionWatcher {
	
	private static final Logger log = LoggerFactory.getLogger(JoinGroup.class);
	
	public void join(String groupName, String memberName)
			throws KeeperException, InterruptedException {
		String path = "/" + groupName + "/" + memberName;
		String createdPath = zk.create(path, null/* data */,
				Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL);
		log.info("Created {} ", createdPath);
	}

	public static void main(String[] args) throws Exception {
		if(args == null || args.length != 3){
			args = new String[3];
			args[0] = MyZkConfig.ZK_SERVER_MASTER;			// master server
			args[1] = MyZkConfig.ZK_NODE_GROUP_NAME;		// group name
			args[2] = MyZkConfig.ZK_NODE_MEMBER_NAME;		// group member name
		}
		JoinGroup joinGroup = new JoinGroup();
		joinGroup.connect(args[0]);
		joinGroup.join(args[1], args[2]);

		// stay alive until process is killed or thread is interrupted
		Thread.sleep(Long.MAX_VALUE);
	}
}
