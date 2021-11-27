package cn.lin.rpc.common.protocol;

import cn.lin.rpc.annotation.MessageProtocolAno;
import cn.lin.rpc.common.model.RpcRequest;
import cn.lin.rpc.common.model.RpcResponse;
import cn.lin.rpc.util.SerializingUtil;
import cn.lin.rpc.common.constants.RpcConstant;

/**
 * Protobuf序列化协议
 */
@MessageProtocolAno(RpcConstant.PROTOCOL_PROTOBUF)
public class ProtoBufMessageProtocol implements MessageProtocol{

    @Override
    public byte[] marshallingRequest(RpcRequest request) throws Exception {
        return SerializingUtil.serialize(request);
    }

    @Override
    public RpcRequest unmarshallingRequest(byte[] data) throws Exception {
        return SerializingUtil.deserialize(data,RpcRequest.class);
    }

    @Override
    public byte[] marshallingResponse(RpcResponse response) throws Exception {
        return SerializingUtil.serialize(response);
    }

    @Override
    public RpcResponse unmarshallingResponse(byte[] data) throws Exception {
        return SerializingUtil.deserialize(data,RpcResponse.class);
    }
}
