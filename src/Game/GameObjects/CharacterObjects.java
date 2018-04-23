package Game.GameObjects;

/**
 * Idea: All Characters in the Game are extended from {@link CharacterObjects}
 *       Not all Movable objects need to have the attribute characters needs
 */
public class CharacterObjects extends MovableObject {
    public CharacterObjects(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }
}
