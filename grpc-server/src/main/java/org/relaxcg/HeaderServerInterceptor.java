package org.relaxcg;

import io.grpc.ForwardingServerCall.SimpleForwardingServerCall;
import io.grpc.Metadata;
import io.grpc.ServerCall;
import io.grpc.ServerCallHandler;
import io.grpc.ServerInterceptor;

/**
 * @author relaxcg
 * @date 2024/1/19 17:21
 */
public class HeaderServerInterceptor implements ServerInterceptor {
    @Override
    public <ReqT, RespT> ServerCall.Listener<ReqT> interceptCall(ServerCall<ReqT, RespT> serverCall,
                                                                 Metadata metadata,
                                                                 ServerCallHandler<ReqT, RespT> serverCallHandler) {
        System.out.println("headers received from client:" + metadata);
        String myHeader = metadata.get(Metadata.Key.of("my_header", Metadata.ASCII_STRING_MARSHALLER));
        System.out.println("my header received from client:" + myHeader);
        return serverCallHandler.startCall(new SimpleForwardingServerCall<ReqT, RespT>(serverCall) {

            @Override
            public void sendHeaders(Metadata headers) {
                super.sendHeaders(headers);
            }
        }, metadata);
    }

}
