package Game.GameObjects.Items;

import Game.GameObjects.MovableObject;

public class CrafteObject extends MovableObject {

    private ItemObject item;

    public CrafteObject(double startX, double startY, int width, int height, ItemObject item) {
        super(startX, startY, width, height);
        setColor(java.awt.Color.ORANGE);
        hasHP = true;
        hp = 10;
        maxHP = 10;
        this.item = item;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hp <= 0){
            item.x = x;
            item.y = y;
            world.gameObjects.add(item);
            world.gameObjects.remove(this);
        }
    }
}
