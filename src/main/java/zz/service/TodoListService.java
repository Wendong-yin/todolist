package zz.service;

import zz.model.TodoItem;
import zz.model.TodoList;
import zz.persistence.TodoListPersistence;
import zz.persistence.TodoListPersistenceIJson;
import zz.persistence.TodoListPersistenceLine;

public class TodoListService {


    // in memory model：没有进行持久化处理，关闭程序就消失了。

    private TodoList todoList;
    private TodoListPersistence persistence = new TodoListPersistenceIJson();

    // services

//    public void add(TodoItem item){
//        todoList.getItems().add(item);
//        persistence.save(todoList);
//
//    }

    public void  add(TodoItem todoItem){
        TodoList todoList = get();
        todoList.getItems().add(todoItem);
        persistence.save(todoList);

    }

//    public TodoList get() {
//        if (todoList == null) {
//            todoList = new TodoList();
//            todoList.setTitle("美貌大王的 Todo List");
//            todoList.getItems().add(new TodoItem("Git"));
//            todoList.getItems().add(new TodoItem("swift"));
//            todoList.getItems().add(new TodoItem("react"));
//        }
//
//        return todoList;
//    }

    public TodoList get(){
        TodoList todoList = persistence.read();
        if(todoList == null){
            todoList = new TodoList();
            todoList.setTitle("TO do");
            persistence.save(todoList);

        }
        return todoList;
    }


}
