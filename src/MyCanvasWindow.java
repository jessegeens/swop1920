import java.awt.Graphics;

class MyCanvasWindow extends CanvasWindow {

    protected MyCanvasWindow(String title) {
        super(title);
        // TODO Auto-generated constructor stub
    }

    @Override
    protected void paint(Graphics g) {
        // TODO Auto-generated method stub
        super.paint(g);
    }

    @Override
    protected void handleMouseEvent(int id, int x, int y, int clickCount) {
        // TODO Auto-generated method stub
        super.handleMouseEvent(id, x, y, clickCount);
    }
    
    @Override
    protected void handleKeyEvent(int id, int keyCode, char keyChar) {
        // TODO Auto-generated method stub
        super.handleKeyEvent(id, keyCode, keyChar);
    }
    
}