
package main;


import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import ui.*;
import model.*;

public class MyCanvasWindow extends CanvasWindow {

    public static final int WIDTH = 900;
    public static final int HEIGHT = 900;


    private GlobalController globalController;

    

    


    protected MyCanvasWindow(String title) {
        super(title);
        globalController = new GlobalController();
    }

    @Override
    protected void paint(Graphics g) {
        this.setSize(MyCanvasWindow.WIDTH, MyCanvasWindow.HEIGHT);
        



        
        //TODO UIcontroller rectangle bounds
        
        Rectangle uiBounds = g.getClipBounds();
        //System.out.println(uiBounds);



        //UIPalette palette = new UIPalette(g);
        //UIProgramWindow programArea = new UIProgramWindow(g);


        globalController.renderUIElements(g, uiBounds);
        super.paint(g);

        




    }

    protected void setSize(int width, int height){
        super.width = width;
        super.height = height;
    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        
        super.handleMouseEvent(id, x, y, clickCount);
        globalController.handleMouseEvent(id, x, y, clickCount);
        
        super.repaint();
    }
    
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        
        super.handleKeyEvent(id, keyCode, keyChar);
        globalController.handleKeyEvent(id, keyCode, keyChar);

        super.repaint();
    }

}