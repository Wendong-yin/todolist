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
            todoList.setTitle("美貌大王的 Todo List");
            persistence.save(todoList);

        }
        return todoList;
    }

    // 删除过程先根据传入的String 查找，然后再删除
    public TodoList delete (String name){
        TodoList todoList = get();
        int index = 0;
        while (!todoList.getItems().get(index).getText().equals(name) && index +1 < todoList.getItems().size()){
            index ++;
        }
        todoList.getItems().remove(index);
        // ❤️ 先保存再返回
        persistence.save(todoList);
        return todoList;
    }

    // 修改
    public TodoList modify (String oldname, String newname){
        TodoList todoList = get();
        int index = 0;
        while (!todoList.getItems().get(index).getText().equals(oldname) && index +1 < todoList.getItems().size()){
            index ++;
        }
        todoList.getItems().get(index).setText(newname);

        persistence.save(todoList);
        return todoList;
    }

}
