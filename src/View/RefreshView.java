package View;

public class RefreshView extends Thread{
    public View view;

    public RefreshView(View viewtorefresh){
    this.view=viewtorefresh;}

    @Override
    public void run(){
        while(true){
            view.revalidate();
            view.repaint();
        }
    }
}
