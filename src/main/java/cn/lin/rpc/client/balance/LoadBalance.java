package cn.lin.rpc.client.balance;

import cn.lin.rpc.common.model.Service;

import java.util.List;

/**
 * 负载均衡算法接口
 */
public interface LoadBalance {
    /**
     *
     * @param services
     * @return
     */
    Service chooseOne(List<Service> services);
}
