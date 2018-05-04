package Game.GameObjects.Items;

import javax.imageio.ImageIO;

import Game.GameObjects.SWATTeamMate;
import Game.GameObjects.CharacterObjects.CharacterObject;

import java.awt.Graphics;
import java.io.File;
import java.io.IOException;

public class SWATItem extends ItemObject{

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
    public void draw(Graphics graphics) {
        super.draw(graphics);
    }

    @Override
    public void applyItem(CharacterObject obj) {
        super.applyItem(obj);
        if(!world.player.mate) {
        	 world.gameObjects.add(new SWATTeamMate(world.player.x-world.player.width, world.player.y- world.player.height, 30, 30));
        	 world.player.mate = true;
        }
    }
}
