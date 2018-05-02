package Game.GameObjects.CharacterObjects.Enemies;

import Game.AudioPlayer;
import Game.GameObjects.Bullets.ShootBullet;

public class FlyingEnemy extends EnemyObject {

	public FlyingEnemy(double startX, double startY, int width, int height) {
		super(startX, startY, width, height);

	}

	@Override
	public void move(double diffSeconds) {
		oldX = x;
		oldY = y;
		x += xSpeed * diffSeconds;
		y += ySpeed * diffSeconds;

		double diffX = x - world.player.x;
		double diffY = y - world.player.y;
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

	public void shootBullet() {
		ShootBullet bullet;

		bullet = new ShootBullet(x + width / 2, y + height / 2, 5, 5);
		bullet.alfa = Math.atan2(world.player.y - y, world.player.x - x);

		bullet.isPlayerBullet = false;

		world.bulletObjects.add(bullet);
		AudioPlayer.shortSound(".\\src\\Game\\Sounds\\laser.wav", 0.25);
	}
}
