package Game.GameObjects;

import Game.GameObjects.Items.ItemObject;

import java.awt.*;
import java.util.List;

public class FixedPlattform extends Plattform {
    public FixedPlattform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = true;
        isSolid = true;
        setColor(Color.yellow);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(dropItem){
            if(hp <= 0){
                /*
                ItemObject item = ItemObject.createRandomItem();
                item.x = x;
                item.y = y;
                */
                Explosion explosion = new Explosion(x,y,200);
                explosion.explode();
                world.gameObjects.add(explosion);
                world.gameObjects.remove(this);
            }
        }
    }
}
