package Game.GameObjects.CharacterObjects.Enemies;


import Game.AudioPlayer;
import Game.GameObjects.GameObject;
import Game.GameObjects.Bullets.ExplodeAbleBullet;
import Game.GameObjects.Bullets.SpiderBullet;
import Game.GameObjects.Items.HealthItem;
import Game.GameObjects.Items.ItemObject;
import Game.GameObjects.Items.RapidFireItem;

public class FinalBoss extends Boss {
	
	int random;
	boolean randomBool;
	boolean patternIsSet = false, xDropPosition = true, drop = false;
	double diffX = 6800;

    public FinalBoss(double startX, double startY, int width, int height) {
        super(startX, startY, width, height);
        this.maxHP = 50;
    }

    @Override
    public void move(double diffSeconds) {
        super.move(diffSeconds);

        if(randomBool == false) {
        	random = rnd.nextInt(3)+1;
        	
        }
        
      
        if(random == 1) {
        	pattern1(diffSeconds);
        } else if(random == 2) {
        	
        }else {
        	
        }
      
        
        if(bulletCooldown > 0) {
            bulletCooldown -= diffSeconds;

        } else {
            bulletCooldown = 0.3;
            shootBullet();
        }
           
    }
    
    
    public void pattern1(double diffSeconds) {
    	randomBool = true;
    	isSolid = true;
		isFixed = true;
		hasCollision = false;
    	if(xDropPosition){
    	 diffX = x - world.player.x - (this.width/2);
    	}
		double diffY = 860 - this.height;

		double distanceToPoint = Math.sqrt(Math.pow(diffX, 2) + Math.pow(diffY, 2));
		
		if(drop) {
		if (distanceToPoint > 1) {
			xSpeed = ((-1 / distanceToPoint) * diffX) * 200;
			ySpeed = ((-1 / distanceToPoint) * diffY) * 200;
		}else{
			xSpeed = 0;
			ySpeed = 0;
			drop = true;
		} 
		}
		else {
			isSolid = true;
    		isFixed = true;
    		this.y+=ySpeed*diffSeconds + 30;
    		System.out.println("WAS");
		}
    }
    
    public void drop() {
    	
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
