package Game.GameObjects.Bullets;

import Game.AudioPlayer;
import Game.GameObjects.GameObject;
import Game.GameObjects.Platfrom.FixedPlatform;

import java.awt.*;
import java.util.Random;

public class Spark extends GameObject {

    private int imageC = 0;
    private double fadingTime = 1;
    private double delay = 0.04;
    private int numFrames = 23;
    double xDir;
    double yDir;
    Random rnd;
    int particleOffset;
    FixedPlatform collidingPlatform;
    String hitDir;

    public Spark(double startX, double startY, double bulletXSpeed, double bulletYSpeed, FixedPlatform collidingPlatform, String hitDir) {
        super(startX, startY, 0, 0);
        hasCollision = false;
        isSolid = false;
        isFixed = true;
        destructible = false;
        hp = 1;
        this.collidingPlatform = collidingPlatform;
        xDir = -bulletXSpeed;
        yDir = -bulletYSpeed;
        rnd = new Random();
        particleOffset = rnd.nextInt(10);
        this.hitDir = hitDir;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);
        delay -= diffSeconds;

        fadingTime -= diffSeconds;
        if(fadingTime < 0) {
            fadingTime = 0;
        }
    }


    @Override
    public void draw(Graphics2D graphics) {
        int x = (int) (this.x - world.worldPartX);
        int y = (int) (this.y - world.worldPartY);

        if(hitDir.equals("Top")) {
            y = (int) (collidingPlatform.y-world.worldPartY);
        } else if(hitDir.equals("Bottom")) {
            y = (int) (collidingPlatform.y + collidingPlatform.height-world.worldPartY);
        } else if(hitDir.equals("Left")) {
            x = (int) (collidingPlatform.x-world.worldPartX);
        } else if(hitDir.equals("Right")) {
            x = (int) (collidingPlatform.x + collidingPlatform.width-world.worldPartX);
        }

        int[] xPoints = new int[20];
        int[] yPoints = new int[20];

        xPoints[0] = x;
        yPoints[0] = y;
        /*int inc = -1;
        if(xDir == 0) {
            if(yDir > 0) {
                inc = 1;
            }
            for(int i = 1; i < 20; i++) {
                xPoints[i] = x+(int)((Math.pow(i, 2)/10)*((double)particleOffset/10));
                yPoints[i] = y+i*inc;
            }
        } else {
            if(xDir > 0) {
                inc = 1;
            }
            for(int i = 1; i < 20; i++) {
                xPoints[i] = x+i*inc;
                yPoints[i] = y+(int)((Math.pow(i, 2)/10)*((double)particleOffset/5));
            }

        }*/
        if(hitDir.equals("Top") || hitDir.equals("Bottom")) {
            for(int i = 1; i < 10; i++) {
                yPoints[i] = +(int)(yDir/200*i*particleOffset/10);
                xPoints[i] = -(int)(xDir/200*i)+yPoints[i];
            }
        } else if(hitDir.equals("Left") || hitDir.equals("Right")) {
            for(int i = 1; i < 10; i++) {
                xPoints[i] = +(int)(xDir/200*i*particleOffset/10);
                yPoints[i] = -(int)(yDir/200*i);
            }

        }

        int[] xPoints2 = new int[20];
        int[] yPoints2 = new int[20];

        xPoints2[0] = x;
        yPoints2[0] = y;
        for(int i = 1; i < 10; i++) {
            xPoints2[i] = xPoints[i]/2;
            yPoints2[i] = yPoints[i]/2;
        }

        for(int i = 1; i < 10; i++) {
            xPoints[i] += x;
            yPoints[i] += y;
            xPoints2[i] += x;
            yPoints2[i] += y;
        }




        if(delay <= 0 && imageC < 10){
            delay = 0;
            imageC++;
        }





        if(fadingTime > 0 || imageC < 10) {
                graphics.setColor(new Color(255, 0, 0, (int)(fadingTime*100)));
                graphics.drawPolyline(xPoints, yPoints, imageC);
                graphics.setColor(new Color(255, 255, 0, (int)(fadingTime*100)));
                graphics.drawPolyline(xPoints2, yPoints2, imageC);
        }else{
            hp = 0;
        }


    }

    public void explode(){
        AudioPlayer.shortSound(".\\src\\Game\\Sounds\\boom-bang.aiff",1.0);
    }
}
