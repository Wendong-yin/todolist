package zz.client.net;
//localhost
import com.zzpublic.socket.Connector;
import zz.common.net.Request;
import zz.common.net.Response;

import java.io.IOException;
import java.net.Socket;

public class RequestSender {
    // 客户端发送 Request 返回 Resposne
    public Response send(Request request){
        String serverIp = System.getenv("SERVER_IP");
        String serverPortString = System.getenv("SERVER_PORT");
        System.out.println(serverIp);
        System.out.println(serverPortString);
        int severPort = Integer.valueOf(serverPortString);

        Socket socket = null;
        try{
            // 建立 socket 连接，还没有发送消息 🍰
            socket = new Socket(serverIp,severPort);
        }catch(IOException e){
            e.printStackTrace();
            return null;
        }

        Connector connector = new Connector(socket);
        connector.writeLine(request.getAction());
        connector.writeLine(request.getData());

        return null;

    }
}
