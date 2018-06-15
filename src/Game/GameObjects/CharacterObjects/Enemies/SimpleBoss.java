package Game.GameObjects.CharacterObjects.Enemies;


import Game.AudioPlayer;
import Game.GameObjects.GameObject;
import Game.GameObjects.Bullets.ExplodeAbleBullet;
import Game.GameObjects.Bullets.SpiderBullet;
import Game.GameObjects.Items.HealthItem;
import Game.GameObjects.Items.ItemObject;
import Game.GameObjects.Items.RapidFireItem;

public class SimpleBoss extends Boss {

    public SimpleBoss(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        this.maxHP = 25;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        double distanceToPlayer = GameObject.world.player.x - x;
        if(Math.abs(distanceToPlayer) < 500 || maxHP > hp) {
           if(stronger) {
        	   if(distanceToPlayer > 50 ) {
                   xSpeed = 433;
               } else if(distanceToPlayer < -50) {
                   xSpeed = -433;
               } else {
                   xSpeed = 0;
               }
        	   
           }else {
        	
        	if(distanceToPlayer > 50 ) {
                xSpeed = 100;
            } else if(distanceToPlayer < -50) {
                xSpeed = -100;
            } else {
                xSpeed = 0;
            }
           }

            if(bulletCooldown > 0) {
                bulletCooldown -= diffSeconds;

            } else {
                bulletCooldown = 0.3;
                shootBullet();
            }

            if(specialMoveCooldown > 0) {
                specialMoveCooldown -= diffSeconds;

            } else {
                specialMove();
            }
        }
        if (dropItem) {
            if (hp <= 0) {
                ItemObject item =  new RapidFireItem(0,0);
                ItemObject item2 =  new HealthItem(0,0);
                item.x = x;
                item.y = y;
                item2.x = x+20;
                item2.y = y-20;
                world.gameObjects.add(item);
                world.gameObjects.add(item2);
                world.gameObjects.remove(this);
            }
        }
    }

    @Override
    public void specialMove() {
        ExplodeAbleBullet bullet;

        //ExplosionBullet
        bullet = new ExplodeAbleBullet(x + width/2, y + height/2, 15, 15);
        bullet.alfa  =  specialMoveTick*0.2;
        bullet.range = 200;
        specialMoveTick++;
        specialMoveCooldown = 0.1;
        if(specialMoveTick*0.2 > Math.PI*4) {
            specialMoveCooldown = 5;
            specialMoveTick = 0;
        }
        bullet.speed = 1000;
        bullet.isPlayerBullet = false;
        //world.bulletObjects.add(bullet);

        
        if(stronger) {
        	ExplodeAbleBullet bullet1 = new ExplodeAbleBullet(x + width/2, y + height/2, 5, 5);
            bullet1.alfa  =  specialMoveTick*0.2;
            bullet1.range = 200;
            bullet1.speed = 1000;
            bullet1.isPlayerBullet = false;
            world.gameObjects.add(bullet1);
        }else {
        	//SpiderBullet
            SpiderBullet bullet1 = new SpiderBullet(x + width/2, y + height/2, 5, 5);
            bullet1.alfa  =  specialMoveTick*0.2;
            bullet1.range = 200;
            bullet1.speed = 1000;
            bullet1.isPlayerBullet = false;
            world.gameObjects.add(bullet1);
        }
        

        AudioPlayer.shortSound2(".\\src\\Game\\Sounds\\shot.wav",0.05, world.player.x - x,world.player.y - y);
    }
}
