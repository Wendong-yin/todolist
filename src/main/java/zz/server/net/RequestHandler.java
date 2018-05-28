package zz.server.net;

import com.zzpublic.socket.Connector;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.controller.TodoListController;

public class RequestHandler {
    public void handle(Connector connector){
        String action = connector.readLine();
        String data = connector.readLine();


        // 回复 request 对象
        Request request = new Request();
        request.setAction(action);
        request.setData(data);


        Response response = new Response();

        if(action.equals("add")){
            new TodoListController().add(request,response);
        }else if(action.equals("get")){
            new TodoListController().get(request,response);
        }

    }

}
