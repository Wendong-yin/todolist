package zz.client.service;

import com.google.gson.Gson;
import zz.client.net.RequestSender;
import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.common.net.Request;
import zz.common.net.Response;
import zz.server.persistence.TodoListPersistence;
import zz.server.persistence.TodoListPersistenceIJson;

public class ClientService {
    private TodoList todoList;

    // Service 的目的是把 TodoItem 转化成 Request
    public void  add(TodoItem todoItem){
        Request request = new Request();
        // Request 组成元素之一：action
        request.setAction("add");

        // Request 组成元素之二：序列化的 Json
        String data = new Gson().toJson(todoItem);
        request.setData(data);

        // 发送 Request
        // 因为只用了一次，所以没有生成一个对象
        new RequestSender().send(request);

    }


    public TodoList get(){
        Request request = new Request();
        request.setAction("get");
        // 发送"get"Request，返回的 reponse 中含有 todolist
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

