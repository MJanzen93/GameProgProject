package Game.GameObjects.Weapons;

import Game.GameObjects.Bullets.Explosion;
import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.util.List;

public class Mine extends GameObject {

    private int counter = 100;
    private boolean trigger = false;

    public Mine(double startX, double startY) {
        super(startX, startY, 10, 5);
        isFixed = true;
        isSolid = false;
        destructible = false;
        hasCollision = true;
        hp = 1;
        destructible = true;
        maxHP = 1;
        setColor(new Color(0, 0, 0, 0));
    }

    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(trigger){
            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, width, height);
        }else{
            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(trigger){
            if(counter % 2 == 0){
                setColor(Color.gray);
            }else
                setColor(Color.red);
            if(counter <= 0){
                Explosion explosion = new Explosion(x, y, 200, false);
                explosion.explode();
                hp = 0;
            }
            counter -= diffSeconds;
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
        List<GameObject> collidingObjects = Physics.getCollisions(this);
        for(int i = 0; i < collidingObjects.size(); i++){
            GameObject collidingObject = collidingObjects.get(i);
            if(collidingObject.isPlayer){
                trigger = true;
            }
        }
    }
}
