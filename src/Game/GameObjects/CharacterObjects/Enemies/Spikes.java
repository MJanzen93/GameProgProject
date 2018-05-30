package Game.GameObjects.CharacterObjects.Enemies;

import Game.GameObjects.CharacterObjects.Player;
import Game.GameObjects.GameObject;
import Game.Physics;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.List;

public class Spikes extends GameObject {

    private double timeout = 1;

    public Spikes(double startX, double startY, int width, int height) {
        super(startX, startY+30, width, 20);
        isFixed = true;
        isSolid = true;

        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\spikes.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        graphics.drawImage(image, x, y-30, 50, 50, null, null);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        timeout -= diffSeconds;
    }

    @Override
    public void checkCollision() {
        if(timeout > 0){
            return;
        }
        timeout = 1;
        List<GameObject> collisions =Physics.getCollisions(this);

        for (int i = 0; i < collisions.size(); i++){
            if(collisions.get(i).isPlayer){
                Player player = (Player)collisions.get(i);
                if(player.destructible){
                    player.hp --;
                    player.ySpeed = -800;
                }
            }
        }
    }
}
