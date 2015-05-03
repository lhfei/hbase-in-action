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
import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.apache.zookeeper.KeeperException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @version 0.1
 *
 * @author Hefei Li
 *
 * @since May 3, 2015
 */
public class ConfigUpdater {
	private static final Logger log = LoggerFactory.getLogger(ConfigUpdater.class);

	public static final String PATH = "/config";

	private ActiveKeyValueStore store;
	private Random random = new Random();

	public ConfigUpdater(String hosts) throws IOException, InterruptedException {
		store = new ActiveKeyValueStore();
		store.connect(hosts);
	}

	public void run() throws InterruptedException, KeeperException {
		while (true) {
			String value = random.nextInt(100) + "";
			store.write(PATH, value);
			log.info("Set {} to {}\n", PATH, value);
			TimeUnit.SECONDS.sleep(random.nextInt(10));
		}
	}

	public static void main(String[] args) throws Exception {
		ConfigUpdater configUpdater = new ConfigUpdater(args[0]);
		configUpdater.run();
	}
}
