package zz.model;

public class TodoItem {
    private String text;

    public TodoItem() {

    }

    public TodoItem(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
