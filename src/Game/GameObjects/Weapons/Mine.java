package Game.GameObjects.Weapons;

import Game.GameObjects.Bullets.Explosion;
import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.util.List;

public class Mine extends GameObject {

    private double counter = 0.5;
    private boolean trigger = false;
    private boolean blink = true;

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
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, width, height);
            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
        }else{
            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(trigger){
            if(blink){
                setColor(Color.gray);
                blink = !blink;
            }else {
                setColor(Color.red);
                blink = !blink;
            }
            if(counter <= 0){
                Explosion explosion = new Explosion(x, y, 200, false);
                world.fixedObjects.add(explosion);
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
