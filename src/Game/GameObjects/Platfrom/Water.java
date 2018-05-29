package Game.GameObjects.Platfrom;

import Game.GameObjects.GameObject;
import Game.Physics;

import java.util.List;

public class Water extends GameObject {

    public Water(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        isSolid = false;
        hasCollision = true;
        isFixed= true;
    }

    @Override
    public void checkCollision() {
        List<GameObject> list = Physics.getCollisions(this);
        for (int i = 0; i < list.size(); i++){
            if(list.get(i).isPlayer){
            }
        }
    }
}
