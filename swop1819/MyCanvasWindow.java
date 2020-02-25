package src.canvaswindow;

import src.UI.UIMediator;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * The big window holding everything.
 */
public class MyCanvasWindow extends CanvasWindow {

	private UIMediator mediator;

	/**
	 * Initializes a CanvasWindow object
	 * @param title Window title
	 */
	public MyCanvasWindow(String title) {
		super(title);
		this.show();
		mediator = new UIMediator();
	}



	/**
	 * Method that paints the window.
	 * @param g This object offers the methods that allow you to render on the canvas.
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.LIGHT_GRAY);
		Rectangle bounds = g.getClipBounds();
		g.fillRect(bounds.x, bounds.y, bounds.width, bounds.height);

		mediator.render(g, bounds);
	}

	/**
	 * Method handling the mouse events.
	 * @param id
	 * @param x
	 * @param y
	 * @param clickCount
	 */

	@Override
	protected void handleMouseEvent(int id, int x, int y, int clickCount) {
		super.handleMouseEvent(id, x, y, clickCount);

		mediator.handleMouseEvent(id, x, y, clickCount);
		repaint();
	}

	/**
	 * Method to handle the key event.
	 * @param e
	 */
	@Override
	protected void handleKeyEvent(KeyEvent e) {
		super.handleKeyEvent(e);

		mediator.handleKeyEvent(e);
		repaint();
	}
}