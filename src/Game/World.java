package Game;

import Game.GameObjects.*;
import Game.GameObjects.Enemies.BossObject;
import Game.GameObjects.Enemies.SimpleEnemyObject;
import Game.GameObjects.Enemies.StealerObject;
import Game.GameObjects.Items.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Thread.sleep;

public class World {

    private WorldViewer wViewer;

    public Player player;
    public List<GameObject> gameObjects;
    public List<GameObject> fixedObjects;

    /*LISTS*/
    public List<BulletObject> bulletObjects;


    private Physics physics;
    private InputSystem inputSystem;
    public int enemiesLeft = 4;
    private double diffSeconds;
    private double secondsPassed;

    // top left corner of the displayed pane of the world
    public double worldPartX = 0;
    public double worldPartY = 0;

    private boolean gameOver;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 10;

    public World() {
        physics = new Physics(this);
    }

    public void init()
    {
        //todo
        //GameObject[] gameObjects = MapParser.getGameObjects("");

        gameObjects = new ArrayList<>();
        fixedObjects = new ArrayList<>();
        bulletObjects = new ArrayList<>();

        player = new Player(200, 500);

        //Ground
        fixedObjects.add(new FixedObject(0, 750, 2000, 300));
        fixedObjects.add(new FixedObject(2100, 750, 8000, 300));

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
        gameObjects.add(new SimpleEnemyObject(100, 200, 30, 30));
        gameObjects.add(new SimpleEnemyObject(1100, 200, 30, 30));
        gameObjects.add(new SimpleEnemyObject(1400, 200, 30, 30));
        gameObjects.add(new SimpleEnemyObject(1600, 200, 30, 30));
        gameObjects.add(new StealerObject(1700, 200, 30, 30));
        gameObjects.add(new SWATTeamMate(300, 500, 30, 30));


        //Boss
        gameObjects.add(new BossObject(2550, 300, 100, 100));

        //RapidFireItem
        gameObjects.add(new RapidFireItem(2610, 280));
        gameObjects.add(new RapidFireItem(1010, 280));
        gameObjects.add(new RapidFireItem(1010, 80));

        //SupplyDrop Test
        gameObjects.add(new SupplyDropObject(1500, 300, 50, 50));
        gameObjects.add(new HealthItem(1000, 50));
        gameObjects.add(new JumpItem(1200, 50));
        gameObjects.add(new SpeedUpItem(1300, 50));

        FixedPlattform f = new FixedPlattform(800,400,40,20);
        f.dropItem = true;
        f.hasHP = true;
        f.hp = 10;
        f.maxHP = 10;
        gameObjects.add(f);

        gameObjects.add(player);
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
                fixedObjects.remove(8);
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

            for (int i = 0; i < bulletObjects.size(); i++) {
                bulletObjects.get(i).move(diffSeconds);
                if(bulletObjects.get(i).hp <= 0 && bulletObjects.get(i).hasHP){
                    bulletObjects.remove(bulletObjects.get(i));
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
            for(int i = 0; i < bulletObjects.size(); i++) {
                wViewer.draw(bulletObjects.get(i));
            }
            wViewer.redraw();
            //???
            if(player.bulletCooldown > 0) {
                player.bulletCooldown -= diffSeconds;
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

    //todo player.goLeft() ....
    public void processUserInput() {
        if(inputSystem.leftPressed) {
            player.goLeft();
        } else if (inputSystem.rightPressed) {
            player.goRight();
        } else if((!inputSystem.leftPressed || !inputSystem.rightPressed) && player.xSpeed != 0){
            player.stop();
        }

        if(inputSystem.upPressed && !player.jumping && player.onGround) {
            player.jump();
        }

        if(inputSystem.mousePressed && player.bulletCooldown <= 0) {
            player.shootBullet(inputSystem);
            player.bulletCooldown = player.bulletCooldownfinal; //?? in player ??
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
    private final void adjustWorldPart() {
        final int RIGHT_END = ConstantValues.WORLD_WIDTH - ConstantValues.WORLDPART_WIDTH;
        final int BOTTOM_END = ConstantValues.WORLD_HEIGHT - ConstantValues.WORLDPART_HEIGHT;


        // if avatar is too much right in display ...
        if (player.x > worldPartX + ConstantValues.WORLDPART_WIDTH - ConstantValues.SCROLL_BOUNDS_X) {
            // ... adjust display
            worldPartX = player.x + ConstantValues.SCROLL_BOUNDS_X - ConstantValues.WORLDPART_WIDTH;
            if (worldPartX >= RIGHT_END) {
                worldPartX = RIGHT_END;
            }
        }

        // same left
        else if (player.x < worldPartX + ConstantValues.SCROLL_BOUNDS_X) {
            worldPartX = player.x - ConstantValues.SCROLL_BOUNDS_X;
            if (worldPartX <= 0) {
                worldPartX = 0;
            }
        }

        // same bottom
        if (player.y > worldPartY + ConstantValues.WORLDPART_HEIGHT - ConstantValues.SCROLL_BOUNDS_Y) {
            worldPartY = player.y + ConstantValues.SCROLL_BOUNDS_Y - ConstantValues.WORLDPART_HEIGHT;
            if (worldPartY >= BOTTOM_END) {
                worldPartY = BOTTOM_END;
            }
        }

        // same top
        else if (player.y < worldPartY + ConstantValues.SCROLL_BOUNDS_Y) {
            worldPartY = player.y - ConstantValues.SCROLL_BOUNDS_Y;
            /*
            if (worldPartY <= 0) {
                worldPartY = 0;
            }
            */
        }
    }


    public Physics getPhysics() { return physics; }

}
