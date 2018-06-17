package zz.server.controller;

import com.google.gson.Gson;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.service.TodoListService;

// 在这里相当于中间人，
// 1 接收 request
// 2 Call Service（Call Service）
// 3 发送 response
public class TodoListController {


    private TodoListService service =  new TodoListService();

    public void add(Request request, Response response){
        TodoItem todoItem = new Gson().fromJson(request.getData(), TodoItem.class);
        // 核心功能
        service.add(todoItem);

        response.setStatus("success");
    }

    public void get(Request request, Response response){
        TodoList todoList = service.get();
        // 核心功能
        response.setStatus("success");

        // 写入 Gson 返回Request
        String data = new Gson().toJson(todoList);
        response.setData(data);
    }
}
