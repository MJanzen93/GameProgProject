package Game.GameObjects.Items;

/**
 * Item is static (not move)
 */
public abstract class StaticItemObject extends ItemObject {

    public StaticItemObject(double startX, double startY) {
        super(startX, startY);
    }

    @Override
    public void move(double diffSeconds) {
        //nothing because static object
    }
}
