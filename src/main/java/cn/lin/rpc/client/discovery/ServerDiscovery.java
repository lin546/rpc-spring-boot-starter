package cn.lin.rpc.client.discovery;

import cn.lin.rpc.common.model.Service;

import java.util.List;

/**
 *
 * 服务发现抽象类
 */
public interface ServerDiscovery {

    List<Service> findServiceList(String name);
}
