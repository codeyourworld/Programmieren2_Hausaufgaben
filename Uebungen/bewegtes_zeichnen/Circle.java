package bewegtes_zeichnen;

import java.awt.Color;
import java.util.Arrays;

import view.GameFrameWork;
import view.Oval;
import view.Shape;

public class Circle extends Oval {
	
	private Boolean[] touched_border = new Boolean[4];
	private GameFrameWork framework;
	

	public Circle(int x, int y, int width, int height, Color color, GameFrameWork framework) {
		super(x, y, width, height, color);
		
		// Initialize with no touched Border 
		// TO-DO: Check if borders are touch with starting coordinates
		
		Arrays.fill(touched_border, Boolean.FALSE);
		
		this.framework =framework;

	}

	public void move_down(Shape shape) {
		move_down(shape, 1);
	}
	
	public void move_down(Shape shape, int step ) {
		shape.setY(shape.getY() + step);
	}
	
	
	public void move_up(Shape shape) {
		shape.setY(shape.getY() - 3);
	}
	
	public void move_up(Shape shape, int step) {
		shape.setY(shape.getY() - step);
	}
	
	public void move_left(Shape shape, int step) {
		shape.setX(shape.getX() - step);
	}
	
	public void move_right(Shape shape, int step) {
		shape.setX(shape.getX() + step);
	}
	
	
}
