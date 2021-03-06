package Game.GameObjects.CharacterObjects.Enemies;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

import Game.AudioPlayer;
import Game.Physics;
import Game.GameObjects.GameObject;
import Game.GameObjects.CharacterObjects.Enemies.EnemyObject;
import Game.GameObjects.Platfrom.FixedPlatform;

public class Speedy extends EnemyObject {

	FixedPlatform standingPlatform;
	double counter = 0;
	boolean stateBool = false;
	int acceleration;
	int changer;
	//accelaration spawn right= -1000 spawn left=1000
	public Speedy(double startX, double startY, int with, int height,int acceleration, FixedPlatform standingPlatform) {
		super(startX, startY, with, height);
		destructible = false;
		this.COLOR= Color.PINK;
		isSolid = true;
		isFixed = false;
		hasCollision = true;
		xSpeed = 0;
		isEnemyTouched = true;
		this.acceleration = acceleration;
		this.standingPlatform = standingPlatform;
		
	}

	// All this
	public void move(double diffSeconds) {
		super.move(diffSeconds);

		if (counter > 0) {
			counter = counter - diffSeconds;
			stateBool = false;
			if (x >= (standingPlatform.x + standingPlatform.width-50 ))
				acceleration = -1000;
			if (x <= standingPlatform.x+50)
				acceleration = 1000;
			touchDamge = 2;
			return;
		}

		if (world.player.y <= (standingPlatform.y - standingPlatform.height)
				&& world.player.y >= (standingPlatform.y - standingPlatform.height - 10)
				&& world.player.x <= (standingPlatform.x + standingPlatform.width)
				&& world.player.x >= (standingPlatform.x)) {
			xSpeed = acceleration;
			stateBool = true;

		} else {
			if ((x <= (standingPlatform.x+(this.width/2) )) && stateBool) {
				x=(standingPlatform.x);
				y=(standingPlatform.y-20);
				xSpeed = 0;
				counter = 1;
			}else if( x >= (standingPlatform.x + standingPlatform.width - this.width) && stateBool){
				x=(standingPlatform.x + standingPlatform.width - this.width);
				y=(standingPlatform.y-20);
				xSpeed = 0;
				counter = 1;
			}
		}

	}

	@Override
	public void checkCollision() {
		// TODO Auto-generated method stub
		List<GameObject> collidingObjects = Physics.getCollisions(this);
		for (int i = 0; i < collidingObjects.size(); i++) {
			Game.GameObjects.GameObject collidingObject = collidingObjects.get(i);

			if (collidingObject.isSolid && !collidingObject.isItem && !collidingObject.isEnemy) {
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

				//Check vertical Collision again after setting Y
				if(!(y + height > collidingObject.y && y < collidingObject.y + collidingObject.height)) {
					continue;
				}

				// left side
				if (x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
					x = collidingObject.x - width;
					if (collidingObject.isPlayer) {
						touchedPlayer();
						touchDamge = 0;
					}

				}

				// right side
				if (x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width
						&& xSpeed <= 0) {
					x = collidingObject.x + collidingObject.width;
					if (collidingObject.isPlayer) {
						touchedPlayer();
						touchDamge = 0;
					}
				}
			}

		}

		if (collidingObjects.size() == 0) {
			jumping = true;
			onGround = false;
		}
	}

	public void touchedPlayer() {
		world.player.hitFromObjectBool = true;
		world.player.ySpeed = -800;
		if(acceleration> 0) {
			world.player.hitSide = 1;
		}else {
			world.player.hitSide = -1;
		}
		AudioPlayer.shortSound(".\\src\\Game\\Sounds\\speedyClap.wav",0.15);
		world.player.hp -= touchDamge;
	}

}
