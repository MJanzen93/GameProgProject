package Game.GameObjects.CharacterObjects.Enemies;

import Game.AudioPlayer;
import Game.GameObjects.Bullets.ShootBullet;
import Game.GameObjects.GameObject;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

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

		double diffX = x - world.player.x;
		double diffY = y - world.player.y - world.player.height + 200;
		double distanceToPlayer = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));

		if (Math.abs(distanceToPlayer) < 800 || maxHP > hp) {
			if (distanceToPlayer > 0) {
				xSpeed = ((-1 / distanceToPlayer) * diffX) * 200;
				ySpeed = ((-1 / distanceToPlayer) * diffY) * 200;
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
