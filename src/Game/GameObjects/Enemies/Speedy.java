package Game.GameObjects.Enemies;

import java.awt.Color;
import java.util.List;

import Game.GameObjects.FixedObject;
import Game.GameObjects.GameObject;

public class Speedy extends EnemyObject {
	
	FixedObject standingPlattform;
	int counter = 1;
	boolean stateBool = true;

	public Speedy(double startX, double startY, int with, int height, FixedObject standingPlattform) {
		super(startX, startY, with, height);
		hasHP = false;
		setColor(Color.PINK);
		isCollidable = true;
		xSpeed = 0;
		isEnemyTouched = true;
		this.standingPlattform = standingPlattform;
	}

	// All this
	public void move(double diffSeconds) {
		double oldX = x;
		double oldY = y;

		x += xSpeed * diffSeconds;
		y += ySpeed * diffSeconds;
		
		
		double distanceToPlayer = world.player.x - x;
		if(world.player.y<=(standingPlattform.y-standingPlattform.height) && Math.abs(distanceToPlayer)<standingPlattform.width && stateBool ) {
			if(distanceToPlayer < 600 && x <= (standingPlattform.x+standingPlattform.width-this.width) ) {
				System.out.println("first" + xSpeed);
                xSpeed = 500;
            } else {
            	xSpeed = 0;
            	stateBool = false;
            }
			
		
		}
		if(world.player.y<=(standingPlattform.y-standingPlattform.height) && Math.abs(distanceToPlayer)<standingPlattform.width && !stateBool ) {
			System.out.println("Hieraw1 ");
			if(distanceToPlayer > -600 && x > (standingPlattform.x+this.width) ) {
				System.out.println(" " +x+ "  fd   "+standingPlattform.x+this.width);
				
                xSpeed = -500;
                System.out.println("Hier 2 " + xSpeed);
            } else {
            	System.out.println("Hier 3");
            	xSpeed = 0;
            	stateBool = true;
            }
		}
		
        
		
		
		List<GameObject> collidingObjects = physics.getCollisions(this);
		for (int i = 0; i < collidingObjects.size(); i++) {
			Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

			if (collidingObject.isSolid && !collidingObject.isItem) {
				// check if Enemy is on Object
				if (y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

					y = collidingObject.y - height;
					ySpeed = 0;
					onGround = true;
					jumping = false;

				}

				// check if Enemy is touching bottom side of object
				if (y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height
						&& ySpeed <= 0) {

					y = collidingObject.y + collidingObject.height;
					ySpeed *= 0.99;

				}

				// left side
				if (x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
					x = collidingObject.x - width;
					if (collidingObject.isPlayer) {
						touchedPlayer();
					}

				}

				// right side
				if (x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width
						&& xSpeed <= 0) {
					x = collidingObject.x + collidingObject.width;
					if (collidingObject.isPlayer) {
						touchedPlayer();
					}
				}
			}

		}
		
		

		if (collidingObjects.size() == 0) {
			jumping = true;
			onGround = false;
		}

		if (y + height > 760) {
			y = 760 - height;
			ySpeed = 0;
			onGround = true;
			jumping = false;
		}

	}
	public void touchedPlayer() {
		world.player.hitFromObjectBool = true;
		world.player.ySpeed = -800;
		world.player.hp -= 2;
	}

}
