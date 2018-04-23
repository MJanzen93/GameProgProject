package Game.GameObjects;

import javafx.scene.paint.Color;

import java.util.List;

public class SupplyDropObject extends MovableObject {

    private double fallSpeed = 50;

    public SupplyDropObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        setColor(java.awt.Color.ORANGE);
        hasHP = true;
        hp = 10;
        maxHP = 10;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0){
            world.gameObjects.add(new HealthItem(x,y));
            world.gameObjects.remove(this);
        }
    }
}
