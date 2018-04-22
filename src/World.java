import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class World {

    private WorldViewer wViewer;

    public Player player;
    public List<GameObject> gameObjects;
    public List<GameObject> fixedObjects;
    private Physics physics;
    private InputSystem inputSystem;
    private double bulletCooldown = 0;

    // top left corner of the displayed pane of the world
    double worldPartX = 0;
    double worldPartY = 0;

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
        fixedObjects.add(new FixedObject(0, 750, 10000, 50));

        //Platforms
        fixedObjects.add(new FixedObject(500, 700, 300, 50));
        fixedObjects.add(new FixedObject(600, 600, 200, 30));
        fixedObjects.add(new FixedObject(400, 400, 150, 20));
        fixedObjects.add(new FixedObject(0, 250, 600, 20));
        fixedObjects.add(new FixedObject(650, 250, 300, 20));

        //Enemies
        gameObjects.add(new EnemyObject(100, 200, 30, 30));
        gameObjects.add(new EnemyObject(1100, 200, 30, 30));
        gameObjects.add(new EnemyObject(1400, 200, 30, 30));
        gameObjects.add(new EnemyObject(1600, 200, 30, 30));

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
                try{ Thread.sleep(FRAME_MINIMUM_MILLIS-diffMillis);} catch(Exception ex){}
                currentTick = System.currentTimeMillis();
                diffMillis  = currentTick-lastTick;
            }

            double diffSeconds  = diffMillis/1000.0;
            lastTick            = currentTick;

            processUserInput();
            for(int i = 0; i < gameObjects.size(); i++) {
                gameObjects.get(i).move(diffSeconds);
                if(gameObjects.get(i).hp <= 0) {
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

        if(inputSystem.upPressed && !player.jumping) {
            player.jumping = true;
            player.onGround = false;
            player.ySpeed = -800;
        }

        if(inputSystem.mousePressed && bulletCooldown <= 0) {
            shootBullet();
            bulletCooldown = 0.1;
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
        bullet.alfa  =  Math.atan2(inputSystem.mouseY+worldPartY - player.y, inputSystem.mouseX+worldPartX - player.x);

        bullet.setIsPlayerBullet(true);

        gameObjects.add(bullet);
    }

    public Physics getPhysics() { return physics; }
}
