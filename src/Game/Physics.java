package Game;

import Game.GameObjects.GameObject;

import java.util.ArrayList;
import java.util.List;

public class Physics {

    private static World world;

    private static int fallSpeed = 1500;

    private Physics(World world) {
    }

    public static void setWorld(World _world){
        world = _world;
    }

    @Deprecated
    private void applyGravity(double diffSeconds) {
        for(int i = 0; i < world.gameObjects.size(); i++) {
            GameObject obj = world.gameObjects.get(i);

            if(!obj.onGround && !obj.isFixed)
                obj.ySpeed += fallSpeed*diffSeconds;

        }
        if(world.player.y + world.player.height >= 760) {
            world.player.jumping = false;
        }
    }

    public static void applyGravity(GameObject obj, double diffSeconds){
        //Do not check if isGrounded
        if(!obj.isFixed){
            obj.ySpeed += fallSpeed*diffSeconds;
        }
    }

    /**
     * @param obj
     * @return
     */
    public static List<GameObject> getCollisions(GameObject obj) {

        List<GameObject> collidingObjects = new ArrayList<>();

        if(obj.hasCollision){
            for(int i = 0; i < world.fixedObjects.size(); i++) {

                GameObject fixedObj = world.fixedObjects.get(i);

                if(fixedObj == obj || !fixedObj.hasCollision) {
                    continue;
                }

                //If horizontally overlapping
                if(obj.x + obj.width >= fixedObj.x && obj.x <= fixedObj.x + fixedObj.width) {
                    //If vertically overlapping
                    if(obj.y + obj.height >= fixedObj.y && obj.y <= fixedObj.y + fixedObj.height) {
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
                    if(obj.y + obj.height >= gameObj.y && obj.y <= gameObj.y + gameObj.height) {
                        collidingObjects.add(gameObj);
                    }

                }

            }
        }

        return collidingObjects;
    }
}
