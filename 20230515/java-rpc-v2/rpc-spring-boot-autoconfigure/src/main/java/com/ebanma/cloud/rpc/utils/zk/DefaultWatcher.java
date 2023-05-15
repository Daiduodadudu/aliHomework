package com.ebanma.cloud.rpc.utils.zk;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;

/**
 * 给提供者用的
 */
public class DefaultWatcher implements Watcher {
    @Override
    public void process(WatchedEvent event) {
    }
}