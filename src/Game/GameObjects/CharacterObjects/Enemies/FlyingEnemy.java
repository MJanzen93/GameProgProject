package Game.GameObjects.CharacterObjects.Enemies;

import Game.Animation;
import Game.GameObjects.Bullets.SpiderBullet;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class FlyingEnemy extends EnemyObject {

	public double specialMoveCooldown = 3;
	private Animation animation;
	private Image[] images;

	public FlyingEnemy(double startX, double startY) {
		super(startX+115, startY+86, 70, 50);
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

		try {
			images[0] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_00_delay-0.04s.gif"));
			images[1] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_01_delay-0.04s.gif"));
			images[2] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_02_delay-0.04s.gif"));
			images[3] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_03_delay-0.04s.gif"));
			images[4] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_04_delay-0.04s.gif"));
			images[5] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_05_delay-0.04s.gif"));
			images[6] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_06_delay-0.04s.gif"));
			images[7] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_07_delay-0.04s.gif"));
			images[8] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_08_delay-0.04s.gif"));
			images[9] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_09_delay-0.04s.gif"));
			images[10] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_10_delay-0.04s.gif"));
			images[11] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_11_delay-0.04s.gif"));
			images[12] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_12_delay-0.04s.gif"));
			images[13] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_13_delay-0.04s.gif"));
			images[14] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_14_delay-0.04s.gif"));
			images[15] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_15_delay-0.04s.gif"));
			images[16] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_16_delay-0.04s.gif"));
			images[17] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_17_delay-0.04s.gif"));
			images[18] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_18_delay-0.04s.gif"));
			images[19] = ImageIO.read(new File(".\\src\\Game\\Textures\\BatAnimation\\frame_19_delay-0.04s.gif"));
		} catch (IOException e) {
			e.printStackTrace();
		}

		animation = new Animation(images, this);
		animation.repeat = true;
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

		animation.update(diffSeconds);

	}

	@Override
	public void draw(Graphics graphics) {
		int x = (int) (this.x - world.worldPartX);
		int y = (int) (this.y - world.worldPartY);

		animation.draw(graphics, x-115, y-86, 300, 223);
		

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
}
