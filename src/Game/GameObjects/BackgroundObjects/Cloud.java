package Game.GameObjects.BackgroundObjects;

import java.awt.*;

public class Cloud extends BackgroundObject {


    public Cloud(double startX, double startY, int width, int height) {
        super(startX, startY, width, height, ""); //todo path to cloud texture
    }

    @Override
    public void draw(Graphics2D graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        graphics.setColor(new Color(255,255,255, 166));
        graphics.fillRect(x,y,50,50);
        graphics.fillRect(x+50,y+10,50,50);
        graphics.fillRect(x+100,y,50,50);
        graphics.fillRect(x+25,y-40,50,50);
        graphics.fillRect(x+75,y-40,50,50);
    }
}
