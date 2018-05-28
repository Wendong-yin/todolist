package zz.server.controller;

import com.google.gson.Gson;
import zz.common.model.TodoItem;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.service.TodoListService;

public class TodoListController {
    private TodoListService service =  new TodoListService();
    public void add(Request request, Response response){
        Gson gson = new Gson();
        TodoItem todoItem = gson.fromJson(request.getData(), TodoItem.class);
        service.add(todoItem);

    }

    public void get(Request request, Response response){
        service.get();

    }


}
