package Game.GameObjects.CharacterObjects.Enemies;

import Game.AudioPlayer;
import Game.GameObjects.CharacterObjects.Player;
import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.util.List;

public class MiniEnemy extends EnemyObject {

    private double jumpPositionX;
    private int jumpforce = -800;

    public MiniEnemy(double startX, double startY) {
        super(startX, startY, 5, 5);
        maxHP = 1;
        hp = 1;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = world.player.x - x;

        if(Math.abs(distanceToPlayer) < 500 || maxHP > hp) {
            if (distanceToPlayer > 1) {
                xSpeed = 250;
            } else if (distanceToPlayer < 0) {
                xSpeed = -250;
            } else {
                xSpeed = 0;
            }
        }

        if (world.inputSystem.upPressed && !this.jumping) {
            jumpPositionX = world.player.x;
        }

        if (this.x > (jumpPositionX - (world.player.width ))
                && this.x < (jumpPositionX + (world.player.width )) && onGround && !jumping)
            jump();
    }

    @Override
    public void checkCollision() {
        super.checkCollision();

        List<GameObject> collidingObjects = Physics.getCollisions(this);
        for (int i = 0; i < collidingObjects.size(); i++){
            if(collidingObjects.get(i).isPlayer && collidingObjects.get(i).destructible){
                Player player = (Player) collidingObjects.get(i);
                player.hp--;
                hp = 0;
            }
        }
    }

    @Override
    public void draw(Graphics graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(image == null){
            graphics.setColor(COLOR);
            graphics.fillRect(x, y, width, height);
            graphics.setColor(Color.BLACK);
            graphics.drawRect(x, y, width, height);
        }else
            graphics.drawImage(image, x, y, width, height, null, null);

    }

    public void jump() {
        jumpPositionX = 0;
        jumping = true;
        onGround = false;
        ySpeed = -jumpForce;
    }
}
