package Game.GameObjects.Platfrom;

import Game.GameObjects.GameObject;
import Game.Physics;

import java.util.List;

public class MovablePlattform extends Plattform {

    private int counter = 0;

    public MovablePlattform(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        xSpeed = 100;
    }

    @Override
    public void checkCollision() {
        super.checkCollision();

        List<GameObject> collidingObjects = Physics.getCollisions(this);
        for(int i = 0; i < collidingObjects.size(); i++){
            if(collidingObjects.get(i).isPlayer){
                world.player.xSpeed += xSpeed;
                world.player.ySpeed += ySpeed;
            }else
                xSpeed = 0;
        }
    }
}
