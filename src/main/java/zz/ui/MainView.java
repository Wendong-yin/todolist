package zz.ui;

import com.zzpublic.zwing.Button;
import com.zzpublic.zwing.Label;
import com.zzpublic.zwing.TextField;
import com.zzpublic.zwing.View;
import zz.model.TodoItem;
import zz.model.TodoList;
import zz.service.TodoListService;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class MainView extends View {

    // mark - service

    private TodoListService todoListService = new TodoListService();

    // mark - views

    private static final int paddingNormal = 10;
    private static final int paddingSmall = 5; // paddingSmall 是按钮和输入框之间的距离
    private static final int cellHeight = 30;
    private static final int panelHeight = 40;

    private static final int windowPadding = 48; // 距离最低下边框的间距

    private Label titleLabel;
    private Button addButton;
    private TextField textField;
    private View containerView;

    @Override
    protected void initSubviews() {
        super.initSubviews();

        titleLabel = new Label();
        titleLabel.setLocation(paddingNormal, paddingNormal);
        titleLabel.setSize(this.getWidth() - 2 * paddingNormal, cellHeight);
        titleLabel.setText("");
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel);

        View inputView = new View(); // 用来包裹inputtext 和 添加按钮
        inputView.setSize(this.getWidth(), panelHeight);
        inputView.setLocation(0, this.getHeight() - inputView.getHeight() - windowPadding);
        this.add(inputView);

        addButton = new Button("添加");
        addButton.setSize(100, cellHeight);
        // Button 的location 是相对于 inputView 的
        addButton.setLocation(this.getWidth() - paddingSmall - addButton.getWidth(), paddingSmall);
        inputView.add(addButton);

        textField = new TextField();
        // addButton.getX() 是得到 Button的横坐标，而不是它的宽度
        textField.setSize(addButton.getX() - 2 * paddingSmall, cellHeight);
        textField.setLocation(paddingSmall, paddingSmall);
        inputView.add(textField);
    }

    @Override
    protected void initEvents() {
        super.initEvents();

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                TodoItem todoItem = new TodoItem(textField.getText());
                todoListService.add(todoItem);
                // 添加完成事件之后，清空textfield。
                textField.setText("");

                // 现在完成Service层中LinkedListed写入，然后要完成界面Label的更新
                dataToView();
            }
        });

    }

    @Override
    protected void viewDidDisplay() {
        super.viewDidDisplay();
        dataToView();
    }

    private void dataToView() {
        TodoList todoList = todoListService.get();

        titleLabel.setText(todoList.getTitle());

        // 把之前的内容都删掉，然后再根据linkedlist创建一个新的。
        if (containerView != null) {
            this.remove(containerView);
        }

        containerView = new View();
        containerView.setLocation(0, titleLabel.getY() + titleLabel.getHeight() + paddingNormal);
        containerView.setSize(this.getWidth(), this.getHeight() - windowPadding - containerView.getY() - panelHeight);
        this.add(containerView);



        int y = 0;
        LinkedList<TodoItem> items = todoList.getItems();
        for (TodoItem item : items) {
            Label label = new Label();
            label.setText(item.getText());
            label.setLocation(paddingNormal, y);
            label.setSize(this.getHeight() - 2 * paddingNormal, cellHeight);

            Button delButton = new Button("删除");
            delButton.setSize(100, cellHeight);
            // Button 的location 是相对于 inputView 的
            delButton.setLocation(this.getWidth() - paddingSmall - delButton.getWidth(), y);
            delButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    todoListService.delete(item.getText());
                    // 添加完成事件之后，清空textfield。
                    // 现在完成Service层中LinkedListed写入，然后要完成界面Label的更新
                    dataToView();
                }
            });

            Button modifyButton = new Button("修改");
            modifyButton.setSize(100, cellHeight);
            modifyButton.setLocation(this.getWidth() - 2* paddingSmall - 2* modifyButton.getWidth() , y);
            modifyButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    JFrame frame = new JFrame("新窗口");
                    //设置在屏幕的位置
                    frame.setLocation(100,50);
//              窗体大小
                    frame.setSize(300,60);
//              显示窗体
                    frame.setVisible(true);

                    addButton = new Button("修改");
                    addButton.setSize(100, cellHeight);
                    // Button 的location 是相对于 inputView 的
                    addButton.setLocation(frame.getWidth() - paddingSmall - addButton.getWidth(), paddingSmall);
                    frame.add(addButton);

                    textField = new TextField();
                    // addButton.getX() 是得到 Button的横坐标，而不是它的宽度
                    textField.setSize(0, 40);
                    textField.setLocation(paddingSmall, paddingSmall);
                    frame.add(textField);



                    addButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                            todoListService.modify(item.getText(), textField.getText());
                            dataToView();
                            frame.dispose();
                        }
                    });


                    // 添加完成事件之后，清空textfield。
                    // 现在完成Service层中LinkedListed写入，然后要完成界面Label的更新
                    dataToView();
                }
            });


            containerView.add(delButton);
            containerView.add(modifyButton);

            containerView.add(label);
            y += label.getHeight() + paddingNormal;
        }
    }


}