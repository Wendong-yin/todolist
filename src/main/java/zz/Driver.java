package zz;

import com.zzpublic.zwing.ViewFlow;
import com.zzpublic.zwing.Window;
import zz.ui.MainView;

public class Driver {
    public static void main(String[] args) {
        MainView mainView = new MainView();
        ViewFlow viewFlow = new ViewFlow();
        viewFlow.push(mainView);
        Window window = new Window(viewFlow);
        window.setVisible(true);


        // frame.add()
    }
}
