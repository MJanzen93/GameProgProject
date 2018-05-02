package Game;

import Game.GameObjects.*;
import Game.GameObjects.CharacterObjects.Player;
import Game.GameObjects.CharacterObjects.Enemies.*;
import Game.GameObjects.Items.*;
import Game.GameObjects.Platfrom.FixedPlattform;
import Game.GameObjects.Weapons.Mine;

import java.util.ArrayList;
import java.util.List;

import Game.GameObjects.GameObject;
import Game.GameObjects.SupplyDropObject;
import Game.GameObjects.CharacterObjects.Enemies.Boss1;
import Game.GameObjects.CharacterObjects.Enemies.Speedy;
import Game.GameObjects.Items.HealthItem;
import Game.GameObjects.Items.JumpItem;
import Game.GameObjects.Items.RapidFireItem;
import Game.GameObjects.Items.SpeedUpItem;

import static java.lang.Thread.sleep;

public class World {

    private WorldViewer wViewer;

    public Player player;
    
    public AudioPlayer backgroundPlayer;

    /*LISTS*/
    public List<GameObject> bulletObjects;
    public List<GameObject> gameObjects;
    public List<GameObject> fixedObjects;

    public List<List<GameObject>> allObjects;


    public InputSystem inputSystem;
    public int enemiesLeft = 4;
    private double diffSeconds;
    private double secondsPassed;

    // top left corner of the displayed pane of the world
    public double worldPartX = 0;
    public double worldPartY = 0;

    private boolean gameOver;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 10;

    private boolean GameOver = false;

    public World() {
    }

    public void init()
    {
        //todo
        //List<GameObject> gameObjects = MapParser.getGameObjects("");
    	
    	//Backgound Musik player
    	backgroundPlayer = new AudioPlayer();
    	//backgroundPlayer.backGroundMusic(".\\src\\Game\\Sounds\\megalovania.wav",0.25);

        allObjects = new ArrayList<>();
        gameObjects = new ArrayList<>();
        fixedObjects = new ArrayList<>();
        bulletObjects = new ArrayList<>();

        player = new Player(200, 500);

        createWorld();


        /*
        //Bossroom
        fixedObjects.add(new FixedPlattform(2000, 0, 100, 700));
        //Door
        //fixedObjects.add(new FixedPlattform(2000, 700, 100, 50));
        fixedObjects.add(new FixedPlattform(3300, 0, 100, 700));
        fixedObjects.add(new FixedPlattform(2000, 0, 1300, 100));

        fixedObjects.add(new FixedPlattform(2200, 550, 80, 30));
        fixedObjects.add(new FixedPlattform(2600, 550, 80, 30));
        fixedObjects.add(new FixedPlattform(3000, 550, 80, 30));


        fixedObjects.add(new FixedPlattform(4000, 550, 700, 30));
        */

    }

    void createWorld(){
        //Ground
        fixedObjects.add(new FixedPlattform(0, 750, 2000, 300));
        fixedObjects.add(new FixedPlattform(2100, 750, 8000, 300));

        //Platforms
        fixedObjects.add(new FixedPlattform(500, 700, 300, 50));
        fixedObjects.add(new FixedPlattform(600, 600, 200, 30));
        fixedObjects.add(new FixedPlattform(400, 400, 150, 20));
        fixedObjects.add(new FixedPlattform(0, 250, 600, 20));
        fixedObjects.add(new FixedPlattform(650, 250, 300, 20));

        //Mines
        fixedObjects.add(new Mine(2200, 745));
        fixedObjects.add(new Mine(2300, 745));
        fixedObjects.add(new Mine(2400, 745));
        fixedObjects.add(new Mine(2500, 745));
        fixedObjects.add(new Mine(2600, 745));

        FixedPlattform f = new FixedPlattform(800,400,40,20);
        f.dropItem = true;
        f.destructible = true;
        f.hp = 10;
        f.maxHP = 10;
        f.explodable = true;
        fixedObjects.add(f);
 
        gameObjects.add(new SimpleEnemy(1100, 200, 30, 30));
        //Enemies
        gameObjects.add(new SimpleEnemy(100, 200, 30, 30));
        gameObjects.add(new SimpleEnemy(1100, 200, 30, 30));
        gameObjects.add(new SimpleEnemy(1400, 200, 30, 30));
        gameObjects.add(new SimpleEnemy(1600, 200, 30, 30));
        gameObjects.add(new Stealer(1700, 200, 30, 30));
        gameObjects.add(new SWATTeamMate(300, 500, 30, 30));
        gameObjects.add(new Exploder(1000,500,30,30));


        gameObjects.add(new FlyingEnemy(60,550,120,60));

        fixedObjects.add(new Mine(2200, 745));
        fixedObjects.add(new Mine(2300, 745));
        fixedObjects.add(new Mine(2400, 745));
        fixedObjects.add(new Mine(2500, 745));
        fixedObjects.add(new Mine(2600, 745));


        //Speedy Enemies
        FixedPlattform speedyPlattform1 = new FixedPlattform(0, 250, 600, 20);
        fixedObjects.add(speedyPlattform1);
        gameObjects.add(new Speedy(0, 220, 30, 30,1000,speedyPlattform1));//needs the object where he is on it
        FixedPlattform speedyPlattform2 = new FixedPlattform(4000, 550, 700, 30);
        fixedObjects.add(speedyPlattform2);
        gameObjects.add(new Speedy(4000, 510, 30, 30,1000,speedyPlattform2));//needs the object where he is on it

        //Boss
        gameObjects.add(new Boss1(2550, 300, 100, 100));

        
        FixedPlattform MimicPlattform = new FixedPlattform(4000, 550, 700, 30);
        fixedObjects.add(MimicPlattform);
        gameObjects.add(new Mimic(4700-30, 510, 30, 30,MimicPlattform));
        
        
        
        
        
        //Item
        //SupplyDrop Test
        gameObjects.add(new SupplyDropObject(1500, -300, 50, 50));
        gameObjects.add(new HealthItem(1000, 50));
        gameObjects.add(new JumpItem(1200, 50));
        gameObjects.add(new SpeedUpItem(1300, 50));
        gameObjects.add(new ShieldItem(1500, 50));
        gameObjects.add(new MissileItem(1150,50));
        gameObjects.add(new SWATItem(1050,50));

        gameObjects.add(player);

        allObjects.add(gameObjects);
        allObjects.add(fixedObjects);
        allObjects.add(bulletObjects);
    }

    void run()
    {
        long lastTick = System.currentTimeMillis();
        while(!gameOver)
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

            //update all objects
            for (int i = 0; i < allObjects.size(); i++){
                for(int j = 0; j < allObjects.get(i).size(); j++){
                    allObjects.get(i).get(j).move(diffSeconds);
                    allObjects.get(i).get(j).checkCollision();
                    if(allObjects.get(i).get(j).hp <= 0){
                        allObjects.get(i).remove(allObjects.get(i).get(j));
                    }
                }
            }

            adjustWorldPart();

            wViewer.clear();

            //draw all objects
            for (int i = 0; i < allObjects.size(); i++){
                for(int j = 0; j < allObjects.get(i).size(); j++){
                    wViewer.draw(allObjects.get(i).get(j));
                }
            }

            wViewer.redraw();
            //???
            if(player.bulletCooldown > 0) {
                player.bulletCooldown -= diffSeconds;
            }

            //high ySpeed => get thinner
            if (Math.abs(player.ySpeed) > 500 && player.width > 20) {
                player.width--;
                player.x+=0.5;
                player.height++;
                player.y-=0.5;
                player.checkCollision();
            } else if (player.width < 30) {
                player.width++;
                player.x-=0.5;
                player.height--;
                player.y+=0.5;
                player.checkCollision();
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

        if(inputSystem.upPressed && !player.jumping) {
            player.jump();
        }

        if(inputSystem.mousePressed && player.bulletCooldown <= 0 && !inputSystem.altPressed) {
            player.shootBullet(inputSystem);
            player.bulletCooldown = player.bulletCooldownfinal; //?? in player ??
        }

        if(player.missile > 0 && inputSystem.mousePressed && inputSystem.altPressed){
            player.fireMissels(inputSystem);
        }

        if(inputSystem.downPressed) {
            if (player.width < 40) {
                player.width++;
                player.x-=0.5;
                player.height--;
                player.y+=0.5;
                player.checkCollision();
            }
        } else if (player.width > 30) {
            player.x+=0.5;
            player.y-=0.5;
            player.width--;
            player.height++;
            player.y--;
            player.checkCollision();
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

}
