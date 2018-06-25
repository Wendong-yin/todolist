package zz.server;

import com.zzpublic.socket.Connector;
import zz.server.net.RequestHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) {
        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(9001);
        } catch (IOException e) {
            // 如果别人专用这个端口，就中断了，不继续运行。
            e.printStackTrace();
            return;
        }

        System.out.println("server on");

        while (true) {
            Socket socket = null;
            try {
                // 接收 Request
                socket = serverSocket.accept();
            } catch (IOException e) {
                e.printStackTrace();
                // 如果失败了 继续连接
                continue;
            }

            System.out.println("socket connected");

            Connector connector = new Connector(socket);
            RequestHandler requestHandler = new RequestHandler();
            // ❤️ Handler 真正收到了 Request
            requestHandler.handle(connector);
        }
    }
}