package zz.client.service;

import zz.common.model.TodoItem;
import zz.common.model.TodoList;
import zz.server.persistence.TodoListPersistence;
import zz.server.persistence.TodoListPersistenceIJson;

public class TodoListService {
    private TodoList todoList;

    public void  add(TodoItem todoItem){

    }


    public TodoList get(){
        return todoList;
    }


    public TodoList delete (String name){
        return todoList;
    }

    public TodoList modify (String oldname, String newname){
        return todoList;
    }
}

