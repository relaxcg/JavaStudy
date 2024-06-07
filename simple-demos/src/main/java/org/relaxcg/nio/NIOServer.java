package org.relaxcg.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author relaxcg
 * @date 2024/5/7 16:54
 */
public class NIOServer {

    public static void main(String[] args) throws IOException {
        Selector selector = Selector.open();

        ServerSocketChannel server = ServerSocketChannel.open();
        server.configureBlocking(false);
        server.register(selector, SelectionKey.OP_ACCEPT);
        server.socket().bind(new InetSocketAddress(9000));

        while (true) {
            // 阻塞，直到有事件发生
            selector.select();

            // 获取事件
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 连接事件
                    ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();

                    // 为每个新连接创建一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 将Channel配置为非阻塞模式
                    socketChannel.configureBlocking(false);
                    // 注册Channel
                    socketChannel.register(selector, SelectionKey.OP_READ);
                } else if (key.isReadable()) {
                    // 读事件
                    try (SocketChannel socketChannel = (SocketChannel) key.channel()) {
                        StringBuilder data = new StringBuilder();
                        ByteBuffer buffer = ByteBuffer.allocate(1024);

                        while (true) {
                            // 转换成写模式
                            buffer.clear();
                            if (socketChannel.read(buffer) == -1) {
                                break;
                            }
                            // 切换成读模式
                            buffer.flip();
                            byte[] dst = new byte[buffer.remaining()];
                            System.out.println(buffer.limit() + " " + buffer.remaining());
                            buffer.get(dst);
                            data.append(new String(dst));
                        }
                        System.out.println(data);
                    }
                }
                iterator.remove();
            }
        }

    }
}
