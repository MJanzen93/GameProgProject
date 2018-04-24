package Game.GameObjects.Items;

public class StaticItemObject extends ItemObject {
    public StaticItemObject(double startX, double startY) {
        super(startX, startY);
        isFixed = true;
    }
}
