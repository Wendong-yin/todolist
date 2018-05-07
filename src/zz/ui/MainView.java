package zz.ui;

import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import zz.model.TodoItem;
import zz.model.TodoList;
import zz.service.TodoListService;

import javax.swing.*;
import java.util.LinkedList;

public class MainView extends View {

    // mark - service

    private TodoListService todoListService = new TodoListService();

    // mark - views

    private static final int paddingNormal = 10;
    private static final int paddingSmall = 5;
    private static final int cellHeight = 30;
    private static final int panelHeight = 40;

    private static final int windowPadding = 24;

    private Label titleLabel;

    @Override
    protected void initSubviews() {
        super.initSubviews();

        titleLabel = new Label();
        titleLabel.setLocation(paddingNormal, paddingNormal);
        titleLabel.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
        titleLabel.setText("");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        View inputView = new View();
        inputView.setSize(this.getWidth(), panelHeight);
        inputView.setLocation(0, this.getHeight() - inputView.getHeight() - windowPadding);
        this.add(inputView);

        Button addButton = new Button("添加");
        addButton.setSize(100, cellHeight);
        addButton.setLocation(this.getWidth() - paddingSmall - addButton.getWidth(), paddingSmall);
        inputView.add(addButton);

        TextField textField = new TextField();
        textField.setSize(addButton.getX() - 2 * paddingSmall, cellHeight);
        textField.setLocation(paddingSmall, paddingSmall);
        inputView.add(textField);
    }

    @Override
    protected void viewDidDisplay() {
        super.viewDidDisplay();
        dataToView();
    }

    private void dataToView() {
        TodoList todoList = todoListService.get();

        titleLabel.setText(todoList.getTitle());

        int y = titleLabel.getY() + titleLabel.getHeight() + paddingNormal;

        LinkedList<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            Label label = new Label();
            label.setText(item.getText());
            label.setLocation(paddingNormal, y);
            label.setSize(this.getHeight() - 2 * paddingNormal, cellHeight);
            this.add(label);
            y += label.getHeight() + paddingNormal;
        }
    }
}
