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
            serverSocket = new ServerSocket(9000);
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        System.out.println("server on");

        while (true) {
            Socket socket = null;
            try {
                socket = serverSocket.accept();
                System.out.println("socket connected");
            } catch (IOException e) {
                e.printStackTrace();
                continue;
            }

            System.out.println("socket connected");

            Connector connector = new Connector(socket);
            RequestHandler requestHandler = new RequestHandler();
            requestHandler.handle(connector);
        }
    }
    // zzpublic.com
}