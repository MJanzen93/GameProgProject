package Game.GameObjects.Items;

import Game.GameObjects.CharacterObjects.CharacterObject;
import Game.AudioPlayer;
import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.util.List;
import java.util.Random;

public abstract class ItemObject extends GameObject {

    public static int width = 30;
    public static int height = 30;

    private static Random random = new Random();

    public ItemObject(double startX, double startY) {
        super(startX, startY, width, height);
        isItem = true;
        isSolid = true;
        isFixed = false;
        hp = 1;
        destructible = false;
    }

    public static ItemObject createRandomItem(){
        int num = random.nextInt(9);
        switch (num){
            case 0:
                return new HealthItem(0,0);
            case 1:
                return new DoubleDamageItem(0,0);
            case 2:
                return new JumpItem(0,0);
            case 3:
                return new MissileItem(0,0);
            case 4:
                return new RapidFireItem(0,0);
            case 5:
                return new ShieldItem(0,0);
            case 6:
                return new SpeedUpItem(0,0);
            case 7:
                return new ParachuteItem(0,0);
        }
        return new SWATItem(0,0);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        if(isSolid){
            List<GameObject> collidingObjects = Physics.getCollisions(this);

            for(int i = 0; i < collidingObjects.size(); i++) {
                GameObject collidingObject = collidingObjects.get(i);

                if(collidingObject.isSolid && !collidingObject.isEnemy) {
                    //check if Game.GameObjects.CharacterObject.Player is on Object
                    if(y + height > collidingObject.y && oldY + height <= collidingObject.y && ySpeed >= 0) {

                        y = collidingObject.y - height;
                        ySpeed = 0;
                        onGround = true;
                    }

                    //check if Game.GameObjects.CharacterObject.Player is touching bottom side of object
                    if(y < collidingObject.y + collidingObject.height && oldY >= collidingObject.y + collidingObject.height && ySpeed <= 0) {

                        y = collidingObject.y + collidingObject.height;
                        ySpeed *= 0.99;
                    }

                    //left side
                    if(x + width > collidingObject.x && oldX + width <= collidingObject.x && xSpeed >= 0) {
                        x = collidingObject.x - width;
                        xSpeed = 0;
                    }

                    //right side
                    if(x < collidingObject.x + collidingObject.width && oldX >= collidingObject.x + collidingObject.width && xSpeed <= 0) {
                        x = collidingObject.x + collidingObject.width;
                        xSpeed = 0;
                    }
                }

            }

            if(collidingObjects.size() == 0) {
                onGround = false;
            }

            /*
            if(y + height > 760){
                y = 760-height;
                ySpeed = 0;
                onGround = true;
                jumping = false;
            }
            */
        }
    }

    public void applyItem(CharacterObject obj){
        hp = 0;
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\pickup.aiff",0.15);
    }

    @Override
    public void draw(Graphics2D graphics) {
        super.draw(graphics);
    }
}
