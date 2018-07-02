package Game.GameObjects.CharacterObjects.Enemies;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import Game.AudioPlayer;
import Game.GameObjects.Platfrom.FixedPlatform;

public class Mimic extends EnemyObject {

	FixedPlatform standingPlatform;
	
	public Mimic(double startX, double startY, int width, int height, FixedPlatform standingPlatform) {
		super(startX, startY, width, height);
		this.standingPlatform = standingPlatform;
		this.isSolid = true;
		this.hasCollision = true;
		this.COLOR = Color.YELLOW;
		
	}

	@Override
	public void move(double diffSeconds) {
		super.move(diffSeconds);
		if (world.player.x <= (standingPlatform.x + standingPlatform.width)
				&& world.player.x >= (standingPlatform.x)&&(world.player.y <= (standingPlatform.y - standingPlatform.height)
				&& world.player.y >= (standingPlatform.y - standingPlatform.height - 1000))) {
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
			
			if ((x <= standingPlatform.x) ) {
				x=(standingPlatform.x);
				
			}else if( x >= standingPlatform.x + standingPlatform.width - this.width){
				x=(standingPlatform.x + standingPlatform.width - this.width);
				
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

}
