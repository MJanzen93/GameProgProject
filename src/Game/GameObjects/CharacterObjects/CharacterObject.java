package Game.GameObjects.CharacterObjects;

import Game.GameObjects.GameObject;

import java.awt.*;

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

    public boolean jumping = false;

    public int shieldDuration = 500;

    public int missile = 1;

    public CharacterObject(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        if(hasShield && shieldDuration <= 0) {
            hasShield = false;
            destructible = true;
            shieldDuration = 500;
        }
        if(hasShield){
            shieldDuration -= diffSeconds;
        }
    }

    @Override
    public void checkCollision() {
        super.checkCollision();
    }

    @Override
    public void draw(Graphics graphics) {
        super.draw(graphics);
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(hasShield){
            for (int i = 0; i < 25; i++){
                graphics.setColor(new Color(0, 26,255, 100-i*4));
                graphics.drawOval(x-width/2+i, y-height/2+i, width*2-i*2, height*2-i*2);
            }
        }
        graphics.setColor(COLOR);
        graphics.fillRect(x, y, width, height);
        graphics.setColor(Color.BLACK);
        graphics.drawRect(x, y, width, height);
    }
}
