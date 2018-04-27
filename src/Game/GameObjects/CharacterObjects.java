package Game.GameObjects;

/**
 * Idea: All Characters in the Game are extended from {@link CharacterObjects}
 *       Not all Movable objects need to have the attribute characters needs
 */
public class CharacterObjects extends MovableObject {

    public int damage = 1;
    public int jumpForce = 800;
    public double bulletCooldown = 0.3;
    public double bulletCooldownfinal = 0.3;
    public int xForce = 300;
    public boolean pickUpItem = false;

    public CharacterObjects(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }
}
