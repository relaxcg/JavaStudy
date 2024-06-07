package org.relaxcg.nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

/**
 * @author relaxcg
 * @date 2024/5/8 15:06
 */
public class NIOClient {

    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("127.0.0.1", 9000);
        OutputStream os = socket.getOutputStream();
        os.write("hello world".getBytes(StandardCharsets.UTF_8));
        os.close();
    }
}
