package org.relaxcg;

import io.grpc.ServerBuilder;
import io.grpc.ServerInterceptors;
import org.relaxcg.service.UserService;

import java.io.IOException;
import java.util.Objects;

/**
 * @author relaxcg
 * @date 2024/1/19 15:12
 */
public class Server {

    public static final int PORT = 5001;

    public static void main(String[] args) throws IOException, InterruptedException {
        io.grpc.Server server = ServerBuilder.forPort(PORT)
                .addService(ServerInterceptors.intercept(new UserService(), new HeaderServerInterceptor()))
                .build()
                .start();

        System.out.println("Server started, listening on " + PORT);
        Runtime.getRuntime()
                .addShutdownHook(new Thread(() -> {
                    System.out.println("Shutting down gRPC server since JVM is shutting down.");
                    if (Objects.nonNull(server)) {
                        server.shutdown();
                    }
                    System.out.println("Server shutdown.");
                }));
        server.awaitTermination();
    }
}