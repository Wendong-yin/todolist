package zz.client.service;

import com.google.gson.Gson;
import zz.client.net.RequestSender;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.persistence.TodoListPersistence;
import zz.server.persistence.TodoListPersistenceIJson;

public class TodoListService {
    private TodoList todoList;

    public void  add(TodoItem todoItem){
        Request request = new Request();
        // Request 组成元素之一：action
        request.setAction("add");

        // Request 组成元素之二：序列化的 Json
        String data = new Gson().toJson(todoItem);
        request.setData(data);

        // 发送 Request
        new RequestSender().send(request);

    }


    public TodoList get(){
        Request request = new Request();
        request.setAction("get");

        Response response = new RequestSender().send(request);

        String data = response.getData();
        TodoList todoList = new Gson().fromJson(data, TodoList.class);
        return todoList;
    }


    public TodoList delete (String name){
        return null;
    }

    public TodoList modify (String oldname, String newname){
        return null;
    }
}

