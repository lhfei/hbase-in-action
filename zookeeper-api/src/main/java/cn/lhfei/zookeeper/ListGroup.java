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

import java.util.List;

import org.apache.zookeeper.KeeperException;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since May 3, 2015
 */
public class ListGroup extends ConnectionWatcher {
	public void list(String groupName) throws KeeperException,
			InterruptedException {
		String path = "/" + groupName;

		try {
			List<String> children = zk.getChildren(path, false);
			if (children.isEmpty()) {
				System.out.printf("No members in group %s\n", groupName);
				System.exit(1);
			}
			for (String child : children) {
				System.out.println(child);
			}
		} catch (KeeperException.NoNodeException e) {
			System.out.printf("Group %s does not exist\n", groupName);
			System.exit(1);
		}
	}

	public static void main(String[] args) throws Exception {
		if(args == null || args.length != 2){
			args = new String[2];
			args[0] = MyZkConfig.ZK_SERVER_MASTER;			// master server
			args[1] = MyZkConfig.ZK_NODE_GROUP_NAME;		// group name
		}
		
		ListGroup listGroup = new ListGroup();
		listGroup.connect(args[0]);
		listGroup.list(args[1]);
		listGroup.close();
	}
}
