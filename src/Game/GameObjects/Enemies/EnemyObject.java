package Game.GameObjects.Enemies;

import Game.GameObjects.CharacterObjects;
import Game.GameObjects.Items.ItemObject;
import Game.GameObjects.MovableObject;

import java.awt.*;

/**
 * Enemy
 */
public abstract class EnemyObject extends CharacterObjects {

    public EnemyObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        COLOR = new Color(190, 30, 30);
        isSolid = true;

    }

    @Override
    public void move(double diffSeconds) {
        if(dropItem){
            if(hp <= 0){
                ItemObject item = ItemObject.createItem();
                item.x = x;
                item.y = y;
                world.gameObjects.add(item);
                world.gameObjects.remove(this);
            }
        }
    }
}
