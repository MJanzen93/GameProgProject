package Game.GameObjects.BackgroundObjects;

import Game.ConstantValues;
import Game.GameObjects.GameObject;
import Game.Physics;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class BrokenPart extends BackgroundObject {
    private GameObject originObject;
    private AffineTransformOp op;
    public BrokenPart(GameObject gameObject, int part) {
        super(gameObject.x, gameObject.y);
        this.originObject = gameObject;

        BufferedImage originImage = originObject.image;
        this.isFixed = false;
        isSolid = false;
        hasCollision = false;

        switch(part) {
            case 1:
                this.image = originImage.getSubimage(0, 0, originImage.getWidth()/2, originImage.getHeight()/2);
                xSpeed = -100;
                ySpeed = -200;
                break;
            case 2:
                this.image = originImage.getSubimage(originImage.getWidth()/2, 0,originImage.getWidth()/2, originImage.getHeight()/2);
                x += originImage.getWidth()/2;
                xSpeed = 100;
                ySpeed = -200;
                break;
            case 3:
                this.image = originImage.getSubimage(0, originImage.getHeight()/2, originImage.getWidth()/2, originImage.getHeight()/2);
                y += originImage.getHeight()/2;
                xSpeed = -100;
                ySpeed = -50;
                break;
            case 4:
                this.image = originImage.getSubimage(originImage.getWidth()/2, originImage.getHeight()/2, originImage.getWidth()/2, originImage.getHeight()/2);
                x += originImage.getWidth()/2;
                y += originImage.getHeight()/2;
                xSpeed = 100;
                ySpeed = -50;
                break;
        }

        AffineTransform tx = new AffineTransform();
        tx.rotate(0.1, image.getWidth() / 2, image.getHeight() / 2);

        op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

    }

    @Override
    public void draw(Graphics2D graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        graphics.drawImage(this.image, x, y, this.image.getWidth(), this.image.getHeight(), null);
    }

    @Override
    public void move(double diffSeconds) {
        Physics.applyGravity(this, diffSeconds);
        oldX = x;
        oldY = y;
        x+=xSpeed*diffSeconds;
        y+=ySpeed*diffSeconds;

        image = op.filter(image, null);
        if(y > ConstantValues.WORLDPART_HEIGHT - world.worldPartY) {
            hp = 0;
        }
    }

}
