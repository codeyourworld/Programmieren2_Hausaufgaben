package bewegtes_zeichnen;

import java.awt.Color;

import view.GameFrameWork;
import view.ITickableListener;
import view.Oval;
import view.Rectangle;
import view.Shape;

public class Aufgabe1 implements ITickableListener{

	private static int SCREEN_WIDTH  = 1680;
	private static int SCREEN_HEIGHT = 1050;
	
	Rectangle square1 = new Rectangle(SCREEN_WIDTH / 2 - 70, 0, 70, 70, new Color(255,80,0));
	Oval oval1 =  new Oval(10, 10, 30, 30, new Color(250,200,30));
	Oval oval2 = new Oval(SCREEN_WIDTH - 40,10,30,30, new Color(250,200,30));
	
	Boolean square_move_up = false;
	
	public void init() {
		GameFrameWork framework = new GameFrameWork();
		framework.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
		
		framework.addOval(oval1);
		
		framework.addOval(oval2);
		
		framework.setBackgroundColor(new Color(0,120,120));
		
		framework.addRectangle(square1);
		
		framework.addTick(this);
		
	}

	public void move_down(Shape shape) {
		move_down(shape, 1);
	}
	
	public void move_down(Shape shape, int step ) {
		shape.setY(shape.getY() + step);
	}
	
	
	public void move_up(Shape shape) {
		move_up(shape, 1);;
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
	
	public boolean collided_oval(Oval oval1, Oval oval2) {
		
		return false;
	}
	
	@Override
	public void tick(long elapsedTime) {		
		if (square1.getY() + square1.getHeight() + 3 > SCREEN_HEIGHT) {
			square_move_up = true;
		} else if (square1.getY() - 3 < 0){
			square_move_up = false;
		}
		
		if (square_move_up) move_up(square1);
		else move_down(square1);
		
		move_right(oval1, 1);
		move_left(oval2, 1);

		
	}

}
