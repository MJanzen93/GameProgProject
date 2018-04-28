package Game.GameObjects.Platfrom;

import Game.GameObjects.Bullets.Explosion;
import Game.GameObjects.GameObject;

import java.awt.*;


public class FixedPlattform extends Plattform {
    public FixedPlattform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isFixed = true;
        isSolid = true;

        /*Test Use*/
        explodable = true;
        hasHP = true;
        hp = 10;
        maxHP = 10;
        /**********/
        setColor(Color.yellow);
    }

    @Override
    public void move(double diffSeconds) {
        if(explodable){
            if(hp <= 0){
                /*
                ItemObject item = ItemObject.createRandomItem();
                item.x = x;
                item.y = y;
                */
                Explosion explosion = new Explosion(x+ width/2,y + height/2,200);
                explosion.explode();
                GameObject.world.gameObjects.add(explosion);
                GameObject.world.gameObjects.remove(this);
            }
        }
    }
}
