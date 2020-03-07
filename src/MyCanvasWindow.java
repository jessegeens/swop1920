import java.awt.Graphics;
import java.awt.Rectangle;
import ui.*;
import model.*;

class MyCanvasWindow extends CanvasWindow {



    private UIController uiController;

    //default width and length are currently hardcoded in the CanvasWindow class

    


    protected MyCanvasWindow(String title) {
        super(title);
        uiController = new UIController();
        
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
        //TODO UIcontroller rectangle bounds
        
        Rectangle uiBounds = g.getClipBounds();
        //System.out.println(uiBounds);

        UIPalette palette = new UIPalette(g);
        UIProgramWindow programArea = new UIProgramWindow(g);
        UIGrid grid = new UIGrid(g);
        uiController.renderUIElements(g, uiBounds);


    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        
        super.handleMouseEvent(id, x, y, clickCount);
        uiController.handleMouseEvent(id, x, y, clickCount);
        
        super.repaint();
    }
    
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        
        super.handleKeyEvent(id, keyCode, keyChar);
        uiController.handleKeyEvent(id, keyCode, keyChar);

        super.repaint();
    }

}