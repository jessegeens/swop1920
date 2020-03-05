import java.awt.Graphics;
import ui.*;
import model.*;

class MyCanvasWindow extends CanvasWindow {

    private UIController controller;


    protected MyCanvasWindow(String title) {
        super(title);
        
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        //TODO UIcontroller rectangle bounds

        controller.renderUIlements(g);


    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        // TODO Auto-generated method stub
        super.handleMouseEvent(id, x, y, clickCount);

        controller.handleMouseEvent(id, x, y, clickCount);

        //TODO issues with repaint method
        //this.repaint();
    }
    
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        // TODO Auto-generated method stub
        super.handleKeyEvent(id, keyCode, keyChar);

        controller.handleKeyEvent(id, keyCode, keyChar);

        //this.repaint();
    }

}