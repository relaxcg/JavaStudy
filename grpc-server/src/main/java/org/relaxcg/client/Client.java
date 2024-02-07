package org.relaxcg.client;

import io.grpc.*;
import org.relaxcg.grpc.proto.UserRequest;
import org.relaxcg.grpc.proto.UserResponse;
import org.relaxcg.grpc.proto.UserServiceGrpc;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

/**
 * @author relaxcg
 * @date 2024/1/19 15:54
 */
public class Client {
    private final ManagedChannel originalChannel;
    private final UserServiceGrpc.UserServiceBlockingStub blockingStub;

    private Client() {
        originalChannel = ManagedChannelBuilder.forAddress("localhost", 5001).usePlaintext().build();
        ClientInterceptor interceptor = new HeaderClientInterceptor();
        Channel channel = ClientInterceptors.intercept(originalChannel, interceptor);
        blockingStub = UserServiceGrpc.newBlockingStub(channel);
    }

    private void shutdown() throws InterruptedException {
        originalChannel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private void getUserInfo(String userId) {
        System.out.println("user id:" + userId);
        UserRequest request = UserRequest.newBuilder().setId(userId).build();
        try {
            UserResponse response = blockingStub.getUserInfo(request);
            System.out.println("response:" + response.getEmail());
        } catch (StatusRuntimeException e) {
            System.err.println("RPC failed:" + e.getStatus());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Client client = new Client();
        String userId;
        try (Scanner scanner = new Scanner(System.in)) {
            // 一元
            do {
                System.out.print("userId:");
                userId = scanner.next();
                client.getUserInfo(userId);
            } while (!"-1".equals(userId));
        } finally {
            client.shutdown();
        }
    }
}