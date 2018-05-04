package Game.GameObjects;

import java.util.List;

import Game.AudioPlayer;
import Game.Physics;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.CharacterObjects.Enemies.EnemyObject;

public class SWATTeamMate extends GameObject {

	private double bulletCooldown = 0.5;
	private EnemyObject enemyObject;
	private double jumpPositionX;
	private double jumpForce = 800;

	public SWATTeamMate(double startX, double startY, int width, int height) {
		super(startX, startY, width, height);
		isSolid = true;
		destructible = true;
		hp = 10;
		maxHP = 10;
		isPlayer = true;
	}

	@Override
	public void move(double diffSeconds) {
		super.move(diffSeconds);

		double distanceToPlayer = world.player.x - x;
		if (Math.abs(distanceToPlayer) < 1300) {
			if (distanceToPlayer < 1200 && distanceToPlayer > 100) {
				xSpeed = 300;
			} else if (distanceToPlayer <= 100 && distanceToPlayer > (world.player.width * 2)) {
				xSpeed = 100;
			} else if (distanceToPlayer > -1200 && distanceToPlayer < -100) {
				xSpeed = -300;
			} else if (distanceToPlayer >= -100 && distanceToPlayer < -(world.player.width * 2)) {
				xSpeed = -100;
			} else {
				xSpeed = 0;
			}

			if (world.inputSystem.upPressed && !this.jumping) {
				jumpPositionX = world.player.x;
			}

			if (this.x > (jumpPositionX - (world.player.width ))
					&& this.x < (jumpPositionX + (world.player.width )) && onGround && !jumping)
				jump();
		}

		if (bulletCooldown > 0) {
			bulletCooldown -= diffSeconds;
		} else {
			bulletCooldown = 0.5;
			enemyObject = searchEnemy();
			if (enemyObject != null) {
				shootBullet();
			}

		}
	}

	public void checkCollision() {
		if (isSolid) {
			List<GameObject> collidingObjects = Physics.getCollisions(this);

			for (int i = 0; i < collidingObjects.size(); i++) {
				GameObject collidingObject = collidingObjects.get(i);

				if (collidingObject.isSolid) {
					// check if Game.GameObjects.CharacterObject.Player is on Object
					if (y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

						y = collidingObject.y - height;
						ySpeed = 0;
						onGround = true;
						jumping = false;
					}

					// check if Game.GameObjects.CharacterObject.Player is touching bottom side of
					// object
					if (y < collidingObject.y + collidingObject.height
							&& oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

						y = collidingObject.y + collidingObject.height;
						ySpeed *= 0.99;
					}

					// left side
					if (x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
						x = collidingObject.x - width - 1;
						xSpeed = 0;
						jump();
					}

					// right side
					if (x < collidingObject.x + collidingObject.width
							&& oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
						x = collidingObject.x + collidingObject.width;
						xSpeed = 0;
						jump();

					}

				}
			}
		}

	}

	public void shootBullet() {
		ShootBullet bullet;

		bullet = new ShootBullet(x + width / 2, y + height / 2, 5, 5);
		bullet.alfa = Math.atan2(enemyObject.y - y, enemyObject.x - x);

		bullet.isPlayerBullet = true;

		world.gameObjects.add(bullet);
	}

	private EnemyObject searchEnemy() {
		EnemyObject enemyObject = null;
		for (int i = 0; i < world.gameObjects.size(); i++) {
			if (world.gameObjects.get(i) instanceof EnemyObject) {
				double diffX = x - world.gameObjects.get(i).x - (this.width / 2);
				double diffY = y - world.gameObjects.get(i).y + (this.height / 2);
				double distanceToSwatMate = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
				if (distanceToSwatMate < 600) {
					return (EnemyObject) world.gameObjects.get(i);
				} else {
					return null;
				}
			}
		}
		return enemyObject;
	}

	public void jump() {
		jumpPositionX = 0;
		jumping = true;
		onGround = false;
		ySpeed = -jumpForce;
		AudioPlayer.shortSound(".\\src\\Game\\Sounds\\jump.wav", 0.15);
	}
}
