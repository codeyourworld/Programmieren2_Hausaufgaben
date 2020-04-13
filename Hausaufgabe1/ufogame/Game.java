package ufogame;

import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import view.GameFrameWork;
import view.IKeyboardListener;
import view.ITickableListener;
import view.Message;

public class Game implements ITickableListener, IKeyboardListener {

	// Idea: we want to have multiple instances of an ufo and of a projectile
	private ArrayList<Projectile> projectiles = new ArrayList<>();
	private ArrayList<Ufo> ufos = new ArrayList<>();
//	private Ufo [] ufos = new Ufo[10];
//	private Projectile [] projectiles = new Projectile[30];
	private Ship ship;
	private int screenWidth = 900;
	private int screenHeight = 700;
	private GameFrameWork frameWork = new GameFrameWork();
	private int score = 0; 
	private Message scoreMessage = new Message(Integer.toString(score), 800, 50, 16, new Color(255,255,255));
	/**
	 * Initiates everything for the game. Multiple ufos and a ship are created.
	 */
	public void init() {
		frameWork.setSize(screenWidth, screenHeight);
		frameWork.setBackground(new Background("Hausaufgabe1/assets/space.jpg"));
		
		ship = new Ship(screenWidth / 2, 4 * screenHeight / 5, screenWidth / 9, screenWidth / 9,
				"Hausaufgabe1/assets/ship.png");
		frameWork.addGameObject(ship);

		Ufo ufo = new Ufo(-20, screenHeight / 5, screenWidth / 10, screenWidth / 19, 1,
				"Hausaufgabe1/assets/dino.png");
		ufos.add(ufo);
		frameWork.addGameObject(ufo);

		for (int i = 1; i < 10; i++) {
			ufos.add(new Ufo(ufos.get(i - 1).getX() - 200, ufos.get(0).getY(), ufos.get(0).getWidth(),
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(i));
		}

		frameWork.addTick(this);
		frameWork.addIKeyInput(this);

	}

	public void shoot() {
		// create a projectile
		Projectile projectile = new Projectile(ship.getX() + ship.getWidth()/4, 
				ship.getY() - ship.getWidth() / 2, ship.getWidth(), ship.getHeight(), 3,
				"Hausaufgabe1/assets/avo.png");
		projectiles.add(projectile);

		frameWork.addGameObject(projectile);

//		for(int i = 0; i < 1000; i++) {
//			projectiles.add(projectile);
//		}
//		
//		for(Projectile p : projectiles) {
//			System.out.println(p.getImagePath());
//		}
		// Variante Array
		// projectiles[0] = projectile;

		// projectiles.get(0).getWidth();
		// Variante Array
		// projectiles[0].getWidth();

		// projectiles.size();
		// Variante Array
		// projectiles.lenght
	}

	@Override
	public void tick(long elapsedTime) {
		for (Ufo ufo : ufos) {
			ufo.move();
		}
		if (ufos.get(0).getX() > screenWidth) {
			frameWork.removeGameObject(ufos.get(0));
			ufos.remove(0);
			ufos.add(new Ufo(ufos.get(ufos.size() - 1).getX() - 200, ufos.get(0).getY(), ufos.get(0).getWidth(),
					ufos.get(0).getHeight(), ufos.get(0).getSpeed(), ufos.get(0).getImagePath()));
			frameWork.addGameObject(ufos.get(ufos.size() - 1));
		}
		
		for(Projectile p: projectiles) {
            p.move();
        }
		
		//TODO check size of list
		if (projectiles.size() > 0 && projectiles.get(0).getY() + projectiles.get(0).getHeight() < 0) {
            frameWork.removeGameObject(projectiles.get(0));
            projectiles.remove(0);
        }
		if (checkCollison()) {
			System.out.println("Collision detected!");
			scoreMessage.setMessage(Integer.toString(score));
			frameWork.addMessage(scoreMessage);
		}
	}
	
	public boolean checkCollison() {
		for (Ufo ufo : ufos) {
			int ufoX = ufo.getX();
			int ufoY = ufo.getY();
			
			for (Projectile projectile : projectiles) {
				int projectileX = projectile.getX();
				int projectileY = projectile.getY();
				
				int difference_y = projectileY - (ufoY + ufo.getHeight());
				if ((projectileX > ufoX && projectileX < ufoX + ufo.getWidth()) && 
					difference_y < 3 && difference_y > 0) {
					score++;
					frameWork.removeGameObject(ufo);
					ufos.remove(ufo);
					frameWork.removeGameObject(projectile);
					projectiles.remove(projectile);
					return true;
				} 
			}
		}
		
		return false;
	}

	@Override
	public int[] getKeys() {
		int [] keys = {KeyEvent.VK_SPACE};
		return keys;
	}

	@Override
	public void keyDown(int key) {
		shoot();
		
	}

}
