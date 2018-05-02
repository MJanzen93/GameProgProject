package Game.GameObjects.CharacterObjects.Enemies;

import Game.AudioPlayer;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.Platfrom.FixedPlattform;

public class Mimic extends EnemyObject {

	FixedPlattform standingPlattform;
	
	public Mimic(double startX, double startY, int width, int height, FixedPlattform standingPlattform) {
		super(startX, startY, width, height);
		this.standingPlattform = standingPlattform;
		this.isSolid = true;
		this.hasCollision = true;
	}

	@Override
	public void move(double diffSeconds) {
		super.move(diffSeconds);
		if (world.player.x <= (standingPlattform.x + standingPlattform.width)
				&& world.player.x >= (standingPlattform.x)&&(world.player.y <= (standingPlattform.y - standingPlattform.height)
				&& world.player.y >= (standingPlattform.y - standingPlattform.height - 1000))) {
			if (world.inputSystem.leftPressed) {
				goLeft();
			} else if (world.inputSystem.rightPressed) {
				goRight();
			} else if ((!world.inputSystem.leftPressed || !world.inputSystem.rightPressed) && xSpeed != 0) {
				stop();
			}

			if (world.inputSystem.upPressed && !this.jumping) {
				jump();
			}
			if (world.inputSystem.mousePressed && this.bulletCooldown <= 0 && !world.inputSystem.altPressed) {
				shootBullet();
			}
			
			if ((x <= standingPlattform.x) ) {
				x=(standingPlattform.x);
				
			}else if( x >= standingPlattform.x + standingPlattform.width - this.width){
				x=(standingPlattform.x + standingPlattform.width - this.width);
				
			}
		} else {
			xSpeed = 0;
		}
		if(bulletCooldown > 0) {
            this.bulletCooldown -= diffSeconds;
        } else {
            this.bulletCooldown = 1;
        }
		
	}

	@Override
	public void checkCollision() {
		super.checkCollision();
	}

	/**
	 * Player goes left
	 */
	public void goLeft() {
		xSpeed = xForce;
	}

	/**
	 * Player goes right
	 */
	public void goRight() {
		xSpeed = -xForce;
	}

	/**
	 * Player jumps
	 */
	public void jump() {
		jumping = true;
		onGround = false;
		ySpeed = -jumpForce;
		AudioPlayer.shortSound(".\\src\\Game\\Sounds\\jump.wav", 0.15);
	}

	/**
	 * Player stops
	 */
	public void stop() {
		xSpeed = 0;
	}

	public void shootBullet() {
		ShootBullet bullet;

		bullet = new ShootBullet(x + width / 2, y + height / 2, 5, 5);
		bullet.alfa = Math.atan2(world.player.y - y, world.player.x - x);

		bullet.isPlayerBullet = false;

		world.bulletObjects.add(bullet);
		AudioPlayer.shortSound(".\\src\\Game\\Sounds\\shot.wav", 0.05);
	}

}
