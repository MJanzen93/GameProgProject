package Game.GameObjects.CharacterObjects;

import Game.GameObjects.GameObject;
import Game.Physics;

/**
 * Idea: All Characters in the Game are extended from {@link CharacterObject}
 *       Not all Movable objects need to have the attribute characters needs
 */
public class CharacterObject extends GameObject {

    public int damage = 1;

    public double bulletCooldown = 0.3;
    public double bulletCooldownfinal = 0.3;

    public boolean hasShield = false;

    /*X and Y*/
    public int xForce = 300;
    public int jumpForce = 800;

    public boolean pickUpItem = false;

    public CharacterObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }
}
