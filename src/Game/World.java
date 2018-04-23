package Game;

import Game.GameObjects.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class World {

    private WorldViewer wViewer;

    public Player player;
    public List<GameObject> gameObjects;
    public List<GameObject> fixedObjects;
    private Physics physics;
    private InputSystem inputSystem;
    public int enemiesLeft = 4;
    private double bulletCooldown = 0;
    private double diffSeconds;
    private double secondsPassed;

    // top left corner of the displayed pane of the world
    double worldPartX = 0;
    double worldPartY = 0;

    private boolean gameOver;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 10;

    public World() {
        physics = new Physics(this);
    }

    public void init()
    {
        gameObjects = new ArrayList<>();
        fixedObjects = new ArrayList<>();

        player = new Player(200, 500);

        gameObjects.add(player);

        //Ground
        fixedObjects.add(new FixedObject(0, 750, 10000, 300));

        //Platforms
        fixedObjects.add(new FixedObject(500, 700, 300, 50));
        fixedObjects.add(new FixedObject(600, 600, 200, 30));
        fixedObjects.add(new FixedObject(400, 400, 150, 20));
        fixedObjects.add(new FixedObject(0, 250, 600, 20));
        fixedObjects.add(new FixedObject(650, 250, 300, 20));

        //Bossroom
        fixedObjects.add(new FixedObject(2000, 0, 100, 700));
        //Door
        fixedObjects.add(new FixedObject(2000, 700, 100, 50));
        fixedObjects.add(new FixedObject(3300, 0, 100, 700));
        fixedObjects.add(new FixedObject(2000, 0, 1300, 100));
        fixedObjects.add(new FixedObject(2200, 550, 80, 30));
        fixedObjects.add(new FixedObject(2600, 550, 80, 30));
        fixedObjects.add(new FixedObject(3000, 550, 80, 30));

        //Enemies
        gameObjects.add(new EnemyObject(100, 200, 30, 30));
        gameObjects.add(new EnemyObject(1100, 200, 30, 30));
        gameObjects.add(new EnemyObject(1400, 200, 30, 30));
        gameObjects.add(new EnemyObject(1600, 200, 30, 30));

        //Boss
        gameObjects.add(new BossObject(2550, 300, 100, 100));

        //BuffObject
        gameObjects.add(new BuffObject(2610, 280, 30, 30));

        //SupplyDrop Test
        gameObjects.add(new SupplyDropObject(1500, 300, 50, 50));

    }

    void run()
    {
        long lastTick = System.currentTimeMillis();
        while(true)
        {
            // calculate elapsed time
            //
            long currentTick    = System.currentTimeMillis();
            long diffMillis   = (currentTick-lastTick);

            if(diffMillis<FRAME_MINIMUM_MILLIS)
            {
                try{ sleep(FRAME_MINIMUM_MILLIS-diffMillis);} catch(Exception ex){}
                currentTick = System.currentTimeMillis();
                diffMillis  = currentTick-lastTick;
            }

            diffSeconds  = diffMillis/1000.0;
            secondsPassed+=diffSeconds;
            lastTick            = currentTick;

            if(gameOver) {

            }

            if(enemiesLeft <= 0) {
                fixedObjects.remove(7);
                enemiesLeft=100;
            }

            processUserInput();
            for(int i = 0; i < gameObjects.size(); i++) {
                gameObjects.get(i).move(diffSeconds);
                if(gameObjects.get(i).hp <= 0) {
                    if(gameObjects.get(i).hasHP) {
                        enemiesLeft--;
                    }
                    gameObjects.remove(i);

                }

            }

            adjustWorldPart();

            wViewer.clear();
            physics.applyGravity(diffSeconds);

            for(int i = 0; i < gameObjects.size(); i++) {
                wViewer.draw(gameObjects.get(i));
            }
            for(int i = 0; i < fixedObjects.size(); i++) {
                wViewer.draw(fixedObjects.get(i));
            }
            wViewer.redraw();
            if(bulletCooldown > 0) {
                bulletCooldown -= diffSeconds;
            }

            //high ySpeed => get thinner
            if (Math.abs(player.ySpeed) > 500 && player.width > 20) {
                double oldX = player.x;
                double oldY = player.y;
                player.width--;
                player.x+=0.5;
                player.height++;
                player.y-=0.5;
                player.checkCollision(oldX, oldY);
            } else if (player.width < 30) {
                double oldX = player.x;
                double oldY = player.y;
                player.width++;
                player.x-=0.5;
                player.height--;
                player.y+=0.5;
                player.checkCollision(oldX, oldY);
            }

        }
    }


    void setGraphicSystem(WorldViewer wViewer) { this.wViewer = wViewer; }
    void setInputSystem(InputSystem inputSystem) { this.inputSystem = inputSystem; }

    public void processUserInput() {
        if(inputSystem.leftPressed) {
            player.xSpeed = -300;
        } else if (inputSystem.rightPressed) {
            player.xSpeed = 300;
        } else {
            player.xSpeed = 0;
        }

        if(inputSystem.upPressed && !player.jumping && player.onGround) {
            player.jumping = true;
            player.onGround = false;
            player.ySpeed = -800;
        }

        if(inputSystem.mousePressed && bulletCooldown <= 0) {
            shootBullet();
            bulletCooldown = player.bulletCooldown;
        }

        if(inputSystem.downPressed) {
            if (player.width < 40) {
                double oldX = player.x;
                double oldY = player.y;
                player.width++;
                player.x-=0.5;
                player.height--;
                player.y+=0.5;
                player.checkCollision(oldX, oldY);
            }
        } else if (player.width > 30) {
            double oldX = player.x;
            double oldY = player.y;
            player.x+=0.5;
            player.y-=0.5;
            player.width--;
            player.height++;
            player.y--;
            player.checkCollision(oldX, oldY);
        }
    }

    // adjust the displayed pane of the world according to Avatar and Bounds
    //
    private final void adjustWorldPart()
    {
        final int RIGHT_END  = ConstantValues.WORLD_WIDTH- ConstantValues.WORLDPART_WIDTH;
        final int BOTTOM_END = ConstantValues.WORLD_HEIGHT- ConstantValues.WORLDPART_HEIGHT;


        // if avatar is too much right in display ...
        if(player.x > worldPartX+ ConstantValues.WORLDPART_WIDTH- ConstantValues.SCROLL_BOUNDS_X)
        {
            // ... adjust display
            worldPartX = player.x+ ConstantValues.SCROLL_BOUNDS_X- ConstantValues.WORLDPART_WIDTH;
            if(worldPartX >= RIGHT_END)
            { worldPartX = RIGHT_END;
            }
        }

        // same left
        else if(player.x < worldPartX+ ConstantValues.SCROLL_BOUNDS_X)
        {
            worldPartX = player.x- ConstantValues.SCROLL_BOUNDS_X;
            if(worldPartX <=0)
            { worldPartX = 0;
            }
        }

        // same bottom
        if(player.y > worldPartY+ ConstantValues.WORLDPART_HEIGHT- ConstantValues.SCROLL_BOUNDS_Y)
        {
            worldPartY = player.y+ ConstantValues.SCROLL_BOUNDS_Y- ConstantValues.WORLDPART_HEIGHT;
            if(worldPartY >= BOTTOM_END)
            { worldPartY = BOTTOM_END;
            }
        }

        // same top
        else if(player.y < worldPartY+ ConstantValues.SCROLL_BOUNDS_Y)
        {
            worldPartY = player.y- ConstantValues.SCROLL_BOUNDS_Y;
            if(worldPartY <=0)
            { worldPartY = 0;
            }
        }

    }

    public void shootBullet() {
        BulletObject bullet;

        bullet = new BulletObject(player.x+player.width/2, player.y+player.height/2, 5, 5);
        bullet.alfa  =  Math.atan2(inputSystem.mouseY+worldPartY - player.y-player.width/2, inputSystem.mouseX+worldPartX - player.x-player.height/2);
        bullet.damage = 2;
        bullet.setIsPlayerBullet(true);

        gameObjects.add(bullet);
    }

    public Physics getPhysics() { return physics; }

    public ArrayList<GameObject> getVisibleObjects(){
        ArrayList<GameObject> list = new ArrayList<>();
        for(int i = 0; i < fixedObjects.size(); i++){
            //check if obj is not in cameras view
            //For x
            if(gameObjects.get(i).x + gameObjects.get(i).width >= worldPartX || gameObjects.get(i).x <= worldPartX + ConstantValues.WORLDPART_WIDTH){
                list.add(gameObjects.get(i));
            }
            //for y
            if(gameObjects.get(i).y + gameObjects.get(i).height >= worldPartY || gameObjects.get(i).y <= worldPartY + ConstantValues.WORLDPART_HEIGHT){
                list.add(gameObjects.get(i));
            }

        }
        return list;
    }
}
