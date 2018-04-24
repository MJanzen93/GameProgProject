package Game.GameObjects.Enemies;

import Game.GameObjects.MovableObject;

import java.awt.*;

/**
 * Enemy
 */
public abstract class EnemyObject extends MovableObject{

    public double bulletCooldown = 0;

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

    }
}