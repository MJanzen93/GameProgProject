package Game.GameObjects;

import Game.ConstantValues;
import Game.InputSystem;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Bomber extends GameObject {

    private int bombX;
    private int bombCount = 5;
    private double coolDown = 0;

    public Bomber(double startX, double startY) {
        super(startX, startY, 155, 42);
        isSolid = false;
        hasCollision = false;
        isFixed = true;
        try {
            image = ImageIO.read(new File(".\\src\\Game\\Textures\\jet.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        xSpeed = 1000;
        if(x >= bombX - 200 && coolDown <= 0 && bombCount > 0){
            world.gameObjects.add(new Missile(x + width/2 ,y + height));
            coolDown = 0.1;
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
