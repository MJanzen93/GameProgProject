package Game.GameObjects.Weapons;

import Game.GameObjects.GameObject;

public abstract class WeaponObject extends GameObject implements AbleToShoot{
    public WeaponObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }
}
