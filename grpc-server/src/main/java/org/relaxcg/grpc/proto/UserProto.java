// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: User.proto

package org.relaxcg.grpc.proto;

public final class UserProto {
  private UserProto() {}
  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistryLite registry) {
  }

  public static void registerAllExtensions(
      com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions(
        (com.google.protobuf.ExtensionRegistryLite) registry);
  }
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_user_UserRequest_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_user_UserRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
    internal_static_user_UserResponse_descriptor;
  static final 
    com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_user_UserResponse_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor
      getDescriptor() {
    return descriptor;
  }
  private static  com.google.protobuf.Descriptors.FileDescriptor
      descriptor;
  static {
    String[] descriptorData = {
      "\n\nUser.proto\022\004user\032\036google/protobuf/wrap" +
      "pers.proto\"\031\n\013UserRequest\022\n\n\002id\030\001 \001(\t\"T\n" +
      "\014UserResponse\022\n\n\002id\030\001 \001(\t\022\023\n\013phoneNumber" +
      "\030\002 \001(\005\022\r\n\005email\030\003 \001(\t\022\024\n\014serialNumber\030\004 " +
      "\001(\0052\204\002\n\013UserService\0224\n\013getUserInfo\022\021.use" +
      "r.UserRequest\032\022.user.UserResponse\022E\n\020bat" +
      "chGetUserInfo\022\021.user.UserRequest\032\034.googl" +
      "e.protobuf.StringValue(\001\022<\n\021getUserInfoS" +
      "tream\022\021.user.UserRequest\032\022.user.UserResp" +
      "onse0\001\022:\n\rbiGetUserInfo\022\021.user.UserReque" +
      "st\032\022.user.UserResponse(\0010\001B%\n\026org.relaxc" +
      "g.grpc.protoB\tUserProtoP\001b\006proto3"
    };
    com.google.protobuf.Descriptors.FileDescriptor.InternalDescriptorAssigner assigner =
        new com.google.protobuf.Descriptors.FileDescriptor.    InternalDescriptorAssigner() {
          public com.google.protobuf.ExtensionRegistry assignDescriptors(
              com.google.protobuf.Descriptors.FileDescriptor root) {
            descriptor = root;
            return null;
          }
        };
    com.google.protobuf.Descriptors.FileDescriptor
      .internalBuildGeneratedFileFrom(descriptorData,
        new com.google.protobuf.Descriptors.FileDescriptor[] {
          com.google.protobuf.WrappersProto.getDescriptor(),
        }, assigner);
    internal_static_user_UserRequest_descriptor =
      getDescriptor().getMessageTypes().get(0);
    internal_static_user_UserRequest_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_user_UserRequest_descriptor,
        new String[] { "Id", });
    internal_static_user_UserResponse_descriptor =
      getDescriptor().getMessageTypes().get(1);
    internal_static_user_UserResponse_fieldAccessorTable = new
      com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
        internal_static_user_UserResponse_descriptor,
        new String[] { "Id", "PhoneNumber", "Email", "SerialNumber", });
    com.google.protobuf.WrappersProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
