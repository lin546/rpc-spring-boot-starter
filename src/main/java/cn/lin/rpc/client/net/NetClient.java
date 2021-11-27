package cn.lin.rpc.client.net;

import cn.lin.rpc.common.model.Service;
import cn.lin.rpc.common.protocol.MessageProtocol;
import cn.lin.rpc.common.model.RpcRequest;
import cn.lin.rpc.common.model.RpcResponse;

/**
 *
 * 网络请求客户端，定义请求规范
 */
public interface NetClient {

    byte[] sendRequest(byte[] data, Service service) throws InterruptedException;

    RpcResponse sendRequest(RpcRequest rpcRequest, Service service, MessageProtocol messageProtocol);
}
