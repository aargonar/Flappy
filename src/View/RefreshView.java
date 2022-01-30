package View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RefreshView implements ActionListener {
    public View view;
    private Timer timer;


    public RefreshView(View viewtorefresh){
    this.view=viewtorefresh;
        this.timer = new Timer(50, this);
        timer.start();
    }

    public void actionPerformed(ActionEvent event){
        if(event.getSource()==timer) {
            view.revalidate();
            view.repaint();
        }

    }
}
