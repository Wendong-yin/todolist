package zz.server.net;

import com.zzpublic.socket.Connector;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.controller.TodoListController;
import zz.server.controller.UserController;

public class RequestHandler {
    public void handle(Connector connector){
        // 第1步：接收 request
        String action = connector.readLine();
        String data = connector.readLine();


        // 第2步：rebuild request 对象
        Request request = new Request();
        request.setAction(action);
        request.setData(data);


        Response response = new Response();

        // 第3步：routing：发送给 Controller
        if(action.equals("add")){
            new TodoListController().add(request,response);
        }else if(action.equals("get")){
            new TodoListController().get(request,response);
            // 新建用户
        } else if (action.equals("user add")){
            new UserController().add(request,response);
        }

        // 第4步：序列化和反序列化，
        // 但是add 和 get 会有不同的操作，
        // 于是我们在 Controller 进行fromJson和 toJson 的操作
        // 但理论上不应该在 Controller 上进行

        // 第5步：发送 Request
        connector.writeLine(response.getStatus());
        connector.writeLine(response.getData());

        System.out.println("check point");
    }

}
