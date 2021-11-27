package cn.lin.rpc.client.discovery;

import cn.lin.rpc.common.serializer.ZookeeperSerializer;
import cn.lin.rpc.common.constants.RpcConstant;
import cn.lin.rpc.common.model.Service;
import com.alibaba.fastjson.JSON;
import org.I0Itec.zkclient.ZkClient;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 */
public class ZookeeperServerDiscovery implements ServerDiscovery {

    private ZkClient zkClient;

    public ZookeeperServerDiscovery(String zkAddress) {
        zkClient = new ZkClient(zkAddress);
        zkClient.setZkSerializer(new ZookeeperSerializer());
    }


    /**
     * 使用Zookeeper客户端，通过服务名获取服务列表
     * 服务名格式：接口全路径
     *
     * @param name
     * @return
     */
    @Override
    public List<Service> findServiceList(String name) {
        String servicePath = RpcConstant.ZK_SERVICE_PATH + RpcConstant.PATH_DELIMITER + name + "/service";
        List<String> children = zkClient.getChildren(servicePath);
        return Optional.ofNullable(children).orElse(new ArrayList<>()).stream().map(str -> {
            String deCh = null;
            try {
                deCh = URLDecoder.decode(str, RpcConstant.UTF_8);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return JSON.parseObject(deCh, Service.class);
        }).collect(Collectors.toList());
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

}
