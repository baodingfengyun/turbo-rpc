package rpc.turbo.discover;

import rpc.turbo.config.HostPort;

import java.util.Map;

@FunctionalInterface
public interface DiscoverListener {
    /**
     * 当服务发生变化时调用
     *
     * @param serverWithWeight
     */
    void onChange(Map<HostPort, Integer> serverWithWeight);
}
