package zz.persistence;

import zz.model.TodoList;

public interface TodoListPersistence {
    void save(TodoList todoList);

    TodoList read();
}
