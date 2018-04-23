package Game.GameObjects;

public abstract class ItemObject extends MovableObject {

    public static int width = 20;
    public static int height = 20;

    public ItemObject(double startX, double startY) {
        super(startX, startY, width, height);
    }
}
