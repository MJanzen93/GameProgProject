package Game.GameObjects.CharacterObjects.Enemies;

import java.awt.*;
import java.util.Random;

public abstract class Boss extends EnemyObject {

    public double bulletCooldown = 0;
    public Random rnd;
    public double specialMoveCooldown = 5;
    public int specialMoveTick = 0;
    public boolean stronger = false;

    public Boss(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        rnd = new Random();
        destructible = true;
        hp = 50;
        maxHP = 50;
        dropItem = true;
        COLOR = new Color(108, 22, 22);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }


    public void specialMove() {
    }
}
