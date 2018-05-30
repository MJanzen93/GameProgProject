package Game.GameObjects.CharacterObjects.Enemies;

import Game.Animation;
import Game.GameObjects.BackgroundObjects.BrokenPart;
import Game.GameObjects.Bullets.SpiderBullet;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

public class FlyingEnemy extends EnemyObject {

	public double specialMoveCooldown = 3;
	private Animation animation;
	private Image[] images;

	public FlyingEnemy(double startX, double startY) {
		super(startX, startY, 130, 90);
		isSolid = true;
		isFixed = true;
		hasCollision = true;
		images = new Image[20];
		try {
			image = ImageIO.read(new File(".\\src\\Game\\Textures\\bat(1).png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		//width = image.getWidth();
		//height = image.getHeight();

		DecimalFormat dFormat = new DecimalFormat("00");

		try {
			for(int i = 0; i < images.length; i++) {
				images[i] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_" + dFormat.format(i) + ".png"));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

		animation = new Animation(images, this);
		animation.repeat = true;
		animation.speed = 0.03;
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

			if(specialMoveCooldown > 0) {
				specialMoveCooldown -= diffSeconds;
			} else {
				specialMove();
				specialMoveCooldown = 3;
			}
		}

		if(xSpeed < 0) {
			animation.useFlippedImages = true;
		} else if (xSpeed > 0) {
			animation.useFlippedImages = false;
		}
		animation.update(diffSeconds);

		if(hp <= 0) {
			breakApart();
		}

	}

	@Override
	public void draw(Graphics2D graphics) {
		int x = (int) (this.x - world.worldPartX);
		int y = (int) (this.y - world.worldPartY);

		/*HitBox*/
		graphics.setColor(Color.RED);
		graphics.drawRect(x, y, width, height);


		animation.draw(graphics, x-70, y-30, 284, 210);


		if(destructible && this.maxHP > this.hp){

			int hp = this.hp;
			int maxHP = this.maxHP;
			if(this.maxHP < 30) {
				hp = (hp*10/maxHP)*3;
				maxHP = 30;
			}
			graphics.setColor(new Color(201, 0, 0));
			graphics.fillRect((int)x+this.width/2-maxHP/2, (int)y-30, hp, 10);
			graphics.setColor(new Color(0, 0, 0));
			graphics.drawRect((int)x+this.width/2-maxHP/2, (int)y-30, maxHP, 10);
		}
	}

	private void specialMove(){
		//SpiderBullet
		SpiderBullet bullet1 = new SpiderBullet(x + width/2, y + height/2, 5, 5);
		bullet1.alfa  =  Math.atan2(world.player.y + world.player.height/2 - y-height/2, world.player.x + world.player.width/2 - x-width/2);
		bullet1.range = 500;
		bullet1.speed = 1000;
		bullet1.isPlayerBullet = false;
		world.gameObjects.add(bullet1);
	}

	@Override
	public void checkCollision() {
	}

	public void breakApart() {
		world.fixedObjects.add(new BrokenPart(this, 1));
		world.fixedObjects.add(new BrokenPart(this, 2));
		world.fixedObjects.add(new BrokenPart(this, 3));
		world.fixedObjects.add(new BrokenPart(this, 4));
	}
}
