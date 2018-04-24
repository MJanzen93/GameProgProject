package Game.GameObjects;

/**
 * Idea: Objects which extends this abstract class are collidable
 * ************************************************************** EDIT: Probably replace with boolean in GameObject: isCollidable !!!!!!
 */
public abstract class CollidableObjects extends GameObject{
    public CollidableObjects(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }
}
