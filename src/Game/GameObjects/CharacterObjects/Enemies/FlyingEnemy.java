package Game.GameObjects.CharacterObjects.Enemies;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FlyingEnemy extends EnemyObject {

	public FlyingEnemy(double startX, double startY, int width, int height) {
		super(startX, startY, width, height);
		isSolid = true;
		isFixed = true;
		hasCollision = true;
		try {
			image = ImageIO.read(new File(".\\src\\Game\\Textures\\bat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void move(double diffSeconds) {
		super.move(diffSeconds);

		double diffX = x - world.player.x - (this.width/2);
		double diffY = y - world.player.y + (this.height/2);

		double distanceToPlayer = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		if (Math.abs(distanceToPlayer) < 800 || maxHP > hp) {
			if (distanceToPlayer > 1) {
				xSpeed = ((-1 / distanceToPlayer) * diffX) * 200;
				ySpeed = ((-1 / distanceToPlayer) * diffY) * 200;
			}else{
				xSpeed = 0;
				ySpeed = 0;
			}

			if (bulletCooldown > 0) {
				bulletCooldown -= diffSeconds;
			} else {
				bulletCooldown = 1;
				shootBullet();
			}
		}

	}

	@Override
	public void checkCollision() {
	}
}
