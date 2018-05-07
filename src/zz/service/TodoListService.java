package zz.service;

import zz.model.TodoItem;
import zz.model.TodoList;

public class TodoListService {
    private TodoList todoList;

    public void add(TodoItem item){
        todoList.getItems().add(item);
    }

    public TodoList get() {
        if (todoList == null) {
            todoList = new TodoList();
            todoList.setTitle("Todo");
            todoList.getItems().add(new TodoItem("Git"));
            todoList.getItems().add(new TodoItem("swift"));
            todoList.getItems().add(new TodoItem("react"));
        }

        return todoList;
    }
}
