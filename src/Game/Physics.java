package Game;

import Game.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Physics {

    private World world;

    private int fallSpeed = 1500;

    public Physics(World world) {
        this.world = world;
    }

    public void applyPhysics() {

    }

    public void applyGravity(double diffSeconds) {
        for(int i = 0; i < world.gameObjects.size(); i++) {
            GameObject obj = world.gameObjects.get(i);

            if(!obj.onGround)
                obj.ySpeed += fallSpeed*diffSeconds;

        }
        if(world.player.y + world.player.height >= 760) {
            world.player.jumping = false;
        }
    }

    public List<GameObject> getCollisions(GameObject obj) {

        List<GameObject> collidingObjects = new ArrayList<>();


        for(int i = 0; i < world.fixedObjects.size(); i++) {

            GameObject fixedObj = world.fixedObjects.get(i);

            if(!fixedObj.hasCollision) {
                continue;
            }

            //If horizontally overlapping
            if(obj.x + obj.width >= fixedObj.x && obj.x <= fixedObj.x + fixedObj.width) {
                //If vertically overlapping
                if(obj.y + obj.height > fixedObj.y && obj.y < fixedObj.y + fixedObj.height) {
                    collidingObjects.add(fixedObj);
                }

            }

        }

        for(int i = 0; i < world.gameObjects.size(); i++) {

            GameObject gameObj = world.gameObjects.get(i);

            if(gameObj == obj || !gameObj.hasCollision) {
                continue;
            }

            //If horizontally overlapping
            if(obj.x + obj.width >= gameObj.x && obj.x <= gameObj.x + gameObj.width) {
                //If vertically overlapping
                if(obj.y + obj.height > gameObj.y && obj.y < gameObj.y + gameObj.height) {
                    collidingObjects.add(gameObj);
                }

            }

        }



        return collidingObjects;
    }
}
