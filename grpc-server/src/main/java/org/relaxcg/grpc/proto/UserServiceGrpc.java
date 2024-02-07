package org.relaxcg.grpc.proto;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.*;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.*;

/**
 * <pre>
 * 定义一个服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.14.0)",
    comments = "Source: User.proto")
public final class UserServiceGrpc {

  private UserServiceGrpc() {}

  public static final String SERVICE_NAME = "user.UserService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<org.relaxcg.grpc.proto.UserRequest,
          org.relaxcg.grpc.proto.UserResponse> getGetUserInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserInfo",
      requestType = UserRequest.class,
      responseType = UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<UserRequest,
      UserResponse> getGetUserInfoMethod() {
    io.grpc.MethodDescriptor<UserRequest, UserResponse> getGetUserInfoMethod;
    if ((getGetUserInfoMethod = UserServiceGrpc.getGetUserInfoMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserInfoMethod = UserServiceGrpc.getGetUserInfoMethod) == null) {
          UserServiceGrpc.getGetUserInfoMethod = getGetUserInfoMethod = 
              io.grpc.MethodDescriptor.<UserRequest, UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "user.UserService", "getUserInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("getUserInfo"))
                  .build();
          }
        }
     }
     return getGetUserInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UserRequest,
      com.google.protobuf.StringValue> getBatchGetUserInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "batchGetUserInfo",
      requestType = UserRequest.class,
      responseType = com.google.protobuf.StringValue.class,
      methodType = io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
  public static io.grpc.MethodDescriptor<UserRequest,
      com.google.protobuf.StringValue> getBatchGetUserInfoMethod() {
    io.grpc.MethodDescriptor<UserRequest, com.google.protobuf.StringValue> getBatchGetUserInfoMethod;
    if ((getBatchGetUserInfoMethod = UserServiceGrpc.getBatchGetUserInfoMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getBatchGetUserInfoMethod = UserServiceGrpc.getBatchGetUserInfoMethod) == null) {
          UserServiceGrpc.getBatchGetUserInfoMethod = getBatchGetUserInfoMethod = 
              io.grpc.MethodDescriptor.<UserRequest, com.google.protobuf.StringValue>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.CLIENT_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "user.UserService", "batchGetUserInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.google.protobuf.StringValue.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("batchGetUserInfo"))
                  .build();
          }
        }
     }
     return getBatchGetUserInfoMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UserRequest,
      UserResponse> getGetUserInfoStreamMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "getUserInfoStream",
      requestType = UserRequest.class,
      responseType = UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
  public static io.grpc.MethodDescriptor<UserRequest,
      UserResponse> getGetUserInfoStreamMethod() {
    io.grpc.MethodDescriptor<UserRequest, UserResponse> getGetUserInfoStreamMethod;
    if ((getGetUserInfoStreamMethod = UserServiceGrpc.getGetUserInfoStreamMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getGetUserInfoStreamMethod = UserServiceGrpc.getGetUserInfoStreamMethod) == null) {
          UserServiceGrpc.getGetUserInfoStreamMethod = getGetUserInfoStreamMethod = 
              io.grpc.MethodDescriptor.<UserRequest, UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.SERVER_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "user.UserService", "getUserInfoStream"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("getUserInfoStream"))
                  .build();
          }
        }
     }
     return getGetUserInfoStreamMethod;
  }

  private static volatile io.grpc.MethodDescriptor<UserRequest,
      UserResponse> getBiGetUserInfoMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "biGetUserInfo",
      requestType = UserRequest.class,
      responseType = UserResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
  public static io.grpc.MethodDescriptor<UserRequest,
      UserResponse> getBiGetUserInfoMethod() {
    io.grpc.MethodDescriptor<UserRequest, UserResponse> getBiGetUserInfoMethod;
    if ((getBiGetUserInfoMethod = UserServiceGrpc.getBiGetUserInfoMethod) == null) {
      synchronized (UserServiceGrpc.class) {
        if ((getBiGetUserInfoMethod = UserServiceGrpc.getBiGetUserInfoMethod) == null) {
          UserServiceGrpc.getBiGetUserInfoMethod = getBiGetUserInfoMethod = 
              io.grpc.MethodDescriptor.<UserRequest, UserResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.BIDI_STREAMING)
              .setFullMethodName(generateFullMethodName(
                  "user.UserService", "biGetUserInfo"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  UserResponse.getDefaultInstance()))
                  .setSchemaDescriptor(new UserServiceMethodDescriptorSupplier("biGetUserInfo"))
                  .build();
          }
        }
     }
     return getBiGetUserInfoMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static UserServiceStub newStub(io.grpc.Channel channel) {
    return new UserServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static UserServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new UserServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static UserServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new UserServiceFutureStub(channel);
  }

  /**
   * <pre>
   * 定义一个服务
   * </pre>
   */
  public static abstract class UserServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     * 简单模式
     * </pre>
     */
    public void getUserInfo(UserRequest request,
                            io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * 客户端流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<UserRequest> batchGetUserInfo(
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      return asyncUnimplementedStreamingCall(getBatchGetUserInfoMethod(), responseObserver);
    }

    /**
     * <pre>
     * 服务端流
     * </pre>
     */
    public void getUserInfoStream(UserRequest request,
                                  io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      asyncUnimplementedUnaryCall(getGetUserInfoStreamMethod(), responseObserver);
    }

    /**
     * <pre>
     * 双向流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<UserRequest> biGetUserInfo(
        io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      return asyncUnimplementedStreamingCall(getBiGetUserInfoMethod(), responseObserver);
    }

    @Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getGetUserInfoMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                UserRequest,
                UserResponse>(
                  this, METHODID_GET_USER_INFO)))
          .addMethod(
            getBatchGetUserInfoMethod(),
            asyncClientStreamingCall(
              new MethodHandlers<
                UserRequest,
                com.google.protobuf.StringValue>(
                  this, METHODID_BATCH_GET_USER_INFO)))
          .addMethod(
            getGetUserInfoStreamMethod(),
            asyncServerStreamingCall(
              new MethodHandlers<
                UserRequest,
                UserResponse>(
                  this, METHODID_GET_USER_INFO_STREAM)))
          .addMethod(
            getBiGetUserInfoMethod(),
            asyncBidiStreamingCall(
              new MethodHandlers<
                UserRequest,
                UserResponse>(
                  this, METHODID_BI_GET_USER_INFO)))
          .build();
    }
  }

  /**
   * <pre>
   * 定义一个服务
   * </pre>
   */
  public static final class UserServiceStub extends io.grpc.stub.AbstractStub<UserServiceStub> {
    private UserServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UserServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单模式
     * </pre>
     */
    public void getUserInfo(UserRequest request,
                            io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 客户端流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<UserRequest> batchGetUserInfo(
        io.grpc.stub.StreamObserver<com.google.protobuf.StringValue> responseObserver) {
      return asyncClientStreamingCall(
          getChannel().newCall(getBatchGetUserInfoMethod(), getCallOptions()), responseObserver);
    }

    /**
     * <pre>
     * 服务端流
     * </pre>
     */
    public void getUserInfoStream(UserRequest request,
                                  io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      asyncServerStreamingCall(
          getChannel().newCall(getGetUserInfoStreamMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     * 双向流
     * </pre>
     */
    public io.grpc.stub.StreamObserver<UserRequest> biGetUserInfo(
        io.grpc.stub.StreamObserver<UserResponse> responseObserver) {
      return asyncBidiStreamingCall(
          getChannel().newCall(getBiGetUserInfoMethod(), getCallOptions()), responseObserver);
    }
  }

  /**
   * <pre>
   * 定义一个服务
   * </pre>
   */
  public static final class UserServiceBlockingStub extends io.grpc.stub.AbstractStub<UserServiceBlockingStub> {
    private UserServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UserServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单模式
     * </pre>
     */
    public UserResponse getUserInfo(UserRequest request) {
      return blockingUnaryCall(
          getChannel(), getGetUserInfoMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     * 服务端流
     * </pre>
     */
    public java.util.Iterator<UserResponse> getUserInfoStream(
        UserRequest request) {
      return blockingServerStreamingCall(
          getChannel(), getGetUserInfoStreamMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   * 定义一个服务
   * </pre>
   */
  public static final class UserServiceFutureStub extends io.grpc.stub.AbstractStub<UserServiceFutureStub> {
    private UserServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private UserServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @Override
    protected UserServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new UserServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     * 简单模式
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<UserResponse> getUserInfo(
        UserRequest request) {
      return futureUnaryCall(
          getChannel().newCall(getGetUserInfoMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_GET_USER_INFO = 0;
  private static final int METHODID_GET_USER_INFO_STREAM = 1;
  private static final int METHODID_BATCH_GET_USER_INFO = 2;
  private static final int METHODID_BI_GET_USER_INFO = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final UserServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(UserServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_GET_USER_INFO:
          serviceImpl.getUserInfo((UserRequest) request,
              (io.grpc.stub.StreamObserver<UserResponse>) responseObserver);
          break;
        case METHODID_GET_USER_INFO_STREAM:
          serviceImpl.getUserInfoStream((UserRequest) request,
              (io.grpc.stub.StreamObserver<UserResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @Override
    @SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BATCH_GET_USER_INFO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.batchGetUserInfo(
              (io.grpc.stub.StreamObserver<com.google.protobuf.StringValue>) responseObserver);
        case METHODID_BI_GET_USER_INFO:
          return (io.grpc.stub.StreamObserver<Req>) serviceImpl.biGetUserInfo(
              (io.grpc.stub.StreamObserver<UserResponse>) responseObserver);
        default:
          throw new AssertionError();
      }
    }
  }

  private static abstract class UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    UserServiceBaseDescriptorSupplier() {}

    @Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return UserProto.getDescriptor();
    }

    @Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("UserService");
    }
  }

  private static final class UserServiceFileDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier {
    UserServiceFileDescriptorSupplier() {}
  }

  private static final class UserServiceMethodDescriptorSupplier
      extends UserServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    UserServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (UserServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new UserServiceFileDescriptorSupplier())
              .addMethod(getGetUserInfoMethod())
              .addMethod(getBatchGetUserInfoMethod())
              .addMethod(getGetUserInfoStreamMethod())
              .addMethod(getBiGetUserInfoMethod())
              .build();
        }
      }
    }
    return result;
  }
}
