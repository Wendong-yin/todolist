package zz.model;

import java.util.LinkedList;

public class TodoList {

    // mark - title

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // mark - items

    private LinkedList<TodoItem> items = new LinkedList<>();

    public LinkedList<TodoItem> getItems() {
        // 返回的是一个 LinkedList 所以就直接可以使用 Linkedlist 的 API 了
        return items;
    }

    public void setItems(LinkedList<TodoItem> items) {
        this.items = items;
    }
}
