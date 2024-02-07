package org.relaxcg.service;

import io.grpc.stub.StreamObserver;
import org.relaxcg.grpc.proto.UserRequest;
import org.relaxcg.grpc.proto.UserResponse;
import org.relaxcg.grpc.proto.UserServiceGrpc;

/**
 * @author relaxcg
 * @date 2024/1/19 15:45
 */
public class UserService extends UserServiceGrpc.UserServiceImplBase {
    @Override
    public void getUserInfo(UserRequest request, StreamObserver<UserResponse> responseObserver) {
        System.out.println("receive:" + request.getId());
        UserResponse response = UserResponse.newBuilder().setId(request.getId())
                .setEmail("email")
                .setPhoneNumber(123)
                .setSerialNumber(1234).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
