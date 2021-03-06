package Game.GameObjects.Items;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import Game.GameObjects.SWATTeamMate;
import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.GameObjects.CharacterObjects.Player;
import Game.GameObjects.CharacterObjects.Enemies.Exploder;
import Game.GameObjects.CharacterObjects.Enemies.FlyingEnemy;
import Game.GameObjects.CharacterObjects.Enemies.SimpleEnemy;
import Game.GameObjects.CharacterObjects.Enemies.Stealer;

public class SWATItem extends ItemObject {

	public SWATItem(double startX, double startY) {
		super(startX, startY);
		try {
			image = ImageIO.read(new File(".\\src\\Game\\Textures\\swat.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void move(double diffSeconds) {
		super.move(diffSeconds);
	}

	@Override
	public void draw(Graphics2D graphics) {
		super.draw(graphics);
	}

	@Override
	public void applyItem(CharacterObject obj) {
		super.applyItem(obj);
		if (!world.player.mate && (obj instanceof Player)) {
			world.gameObjects.add(new SWATTeamMate(world.player.x - world.player.width,
					world.player.y - world.player.height, 30, 30));
			world.player.mate = true;
		} else if(obj instanceof Stealer) {
			Random rn = new Random();
			switch (rn.nextInt(5)) {
			case 0:
				world.gameObjects.add(new SimpleEnemy(obj.x + 30, obj.y - 20, 30, 30));
				break;
			case 1:
				world.gameObjects.add(new FlyingEnemy(obj.x + 30, obj.y - 50));
				break;
			case 2:
				world.gameObjects.add(new Stealer(obj.x + 30, obj.y - 20, 30, 30));
				break;
			case 3:
				world.gameObjects.add(new Exploder(obj.x + 30, obj.y - 20, 30, 30));
				break;
			}
		}
	}
}
