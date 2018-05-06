package Game.GameObjects;

import Game.ConstantValues;
import Game.InputSystem;

public class Bomber extends GameObject {

    private int bombX;
    private int bombCount = 4;
    private double coolDown = 0;

    public Bomber(double startX, double startY) {
        super(startX, startY, 100, 20);
        isSolid = false;
        hasCollision = false;
        isFixed = true;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        xSpeed = 500;
        if(x >= bombX - 200 && coolDown <= 0 && bombCount > 0){
            world.gameObjects.add(new Missile(x + width/2 ,y + height));
            coolDown = 0.2;
            bombCount--;
        }
        coolDown -= diffSeconds;
        if(x >= (world.worldPartX + ConstantValues.WORLDPART_WIDTH) && bombCount <= 0){
            hp = 0;
        }
    }

    @Override
    public void checkCollision() {
    }

    public void setPosition(InputSystem inputSystem){
        bombX = (int) (inputSystem.mouseX + world.worldPartX);
    }
}
