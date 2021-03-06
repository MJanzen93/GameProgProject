package Game;


import static java.lang.Thread.sleep;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import javax.imageio.ImageIO;

import Game.GameObjects.GameObject;
import Game.GameObjects.SWATTeamMate;
import Game.GameObjects.CharacterObjects.Player;


public class World {

    private WorldViewer wViewer;

    public Player player;

    public AudioPlayer backgroundPlayer;

    /*LISTS*/
    public List<GameObject> bulletObjects;
    public List<GameObject> gameObjects;
    public List<GameObject> fixedObjects;
    public List<GameObject> backgroundObjects;

    public List<List<GameObject>> allObjects;


    public InputSystem inputSystem;
    public int enemiesLeft = 4;
    private double diffSeconds;
    private double secondsPassed;

    // top left corner of the displayed pane of the world
    public double worldPartX = 0;
    public double worldPartY = 0;

    public boolean gameOver;
    private double gameOverTime = 3;
    private boolean fpsThreadRunning = true;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 1;

    private boolean GameOver = false;
    private int level;
    public boolean nextLevel = false;

    public World(int level) {
        this.level = level;
    }

    public void init() {


        //Backgound Musik player
        backgroundPlayer = new AudioPlayer();
        backgroundPlayer.backGroundMusic(".\\src\\Game\\Sounds\\megalovania.wav",0.25);

        allObjects = new ArrayList<>();
        gameObjects = new ArrayList<>();
        fixedObjects = new ArrayList<>();
        bulletObjects = new ArrayList<>();
        backgroundObjects = new ArrayList<>();

        player = new Player(200, -2500);

        createWorld();
    }

    void createWorld() {

        List<List<GameObject>> list = null;
        switch(level) {
            case 1:
                list = MapParser.desert1();
                break;
            case 2:
                list = MapParser.desert2();
                break;
            case 3:
                list = MapParser.desert3();
                break;
            case 4:
                list = MapParser.summer1();
                break;
            case 5:
                list = MapParser.summer2();
                break;
        }

        try {
			wViewer.background = ImageIO.read(new File(".\\src\\Game\\Textures\\DBG.png"));
			//wViewer.background = ImageIO.read(new File(".\\src\\Game\\Textures\\SBG.png"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}


        fixedObjects = list.get(0);
        backgroundObjects = list.get(1);
        gameObjects = list.get(2);

        for (int i = 0; i < gameObjects.size(); i++) {
            if (gameObjects.get(i).isPlayer) {
                player.x = gameObjects.get(i).x;
                player.y = gameObjects.get(i).y;
                gameObjects.remove(i);
                gameObjects.add(player);
            }
        }

        allObjects.add(gameObjects);
        allObjects.add(fixedObjects);
        allObjects.add(bulletObjects);

        Thread fpsThread = new Thread(new Runnable() {
            @Override
            public void run() {
                //print FPS
                diffMillis = 1;
                while(fpsThreadRunning){
                    //System.out.println(1000 / diffMillis);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        fpsThread.start();
    }

    long diffMillis = 1;

    void run() {
        long lastTick = System.currentTimeMillis();
        while (true) {
            // calculate elapsed time
            //
            long currentTick = System.currentTimeMillis();
            diffMillis = (currentTick - lastTick);

            if (diffMillis < FRAME_MINIMUM_MILLIS) {
               try {
                    sleep(FRAME_MINIMUM_MILLIS - diffMillis);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
                currentTick = System.currentTimeMillis();
                diffMillis = currentTick - lastTick;
            }

            diffSeconds = diffMillis / 1000.0;
            secondsPassed += diffSeconds;
            lastTick = currentTick;

            processUserInput();

            //update all objects
            for (int i = 0; i < allObjects.size(); i++) {
                for (int j = 0; j < allObjects.get(i).size(); j++) {
                    allObjects.get(i).get(j).move(diffSeconds);
                    allObjects.get(i).get(j).checkCollision();
                    if (allObjects.get(i).get(j).hp <= 0) {
                        if (allObjects.get(i).get(j) instanceof SWATTeamMate)
                            player.mate = false;
                        /*if(allObjects.get(i).get(j) instanceof Platform) {
                            ((Platform) allObjects.get(i).get(j)).breakApart();
                        }*/
                        if(player.hp == 0){
                            gameOver = true;
                        }
                        allObjects.get(i).remove(allObjects.get(i).get(j));
                    }
                }
            }

            adjustWorldPart();

            wViewer.clear();

            //draw BackgroundObjects
            for (int i = 0; i < backgroundObjects.size(); i++) {
                wViewer.draw(backgroundObjects.get(i));
            }

            //draw all objects
            for (int i = 0; i < allObjects.size(); i++) {
                for (int j = 0; j < allObjects.get(i).size(); j++) {
                    wViewer.draw(allObjects.get(i).get(j));
                }
            }

            wViewer.redraw();
            //???
            if (player.bulletCooldown > 0) {
                player.bulletCooldown -= diffSeconds;
            }




            //print FPS
           //System.out.println(1000 / diffMillis);
           // System.out.println("X: "+ player.x + "Y: "+ player.y);


            if(player.y > ConstantValues.WORLD_HEIGHT) {
                System.out.println("Game Over");
                gameOver = true;
            }

            if(gameOver) {
                gameOverTime -= diffSeconds;
            }

            if(gameOverTime < 0 || nextLevel) {
                fpsThreadRunning = false;
                backgroundPlayer.stopSound();
                return;
            }
        }
    }


    void setGraphicSystem(WorldViewer wViewer) {
        this.wViewer = wViewer;
    }

    void setInputSystem(InputSystem inputSystem) {
        this.inputSystem = inputSystem;
    }

    public void processUserInput() {
        if(gameOver) {
            return;
        }
        if (inputSystem.leftPressed) {
            player.goLeft(inputSystem.moveMagnitude);
        } else if (inputSystem.rightPressed) {
            player.goRight(inputSystem.moveMagnitude);
        } else if ((!inputSystem.leftPressed || !inputSystem.rightPressed) && player.xSpeed != 0){
            player.stop();
        }

        if (inputSystem.upPressed && !player.jumping) {
            player.jump();
        }

        if (inputSystem.mousePressed && player.bulletCooldown <= 0 && !inputSystem.altPressed) {
            player.shootBullet(inputSystem);
            player.bulletCooldown = player.bulletCooldownfinal; //?? in player ??
        }

        if(inputSystem.shootMagnitude > 0.25 && player.bulletCooldown <= 0 && !inputSystem.altPressed) {
            player.shootBullet(inputSystem.shootDirection);
            player.bulletCooldown = player.bulletCooldownfinal;
        }

        if (player.missile > 0 && ((inputSystem.mousePressed && inputSystem.altPressed) || inputSystem.yPressed)) {
            player.fireMissels(inputSystem);
        }

        if (player.missile > 0 && inputSystem.yPressed) {

        }

        if(player.hasParachuteItem && inputSystem.downPressed && !player.onGround){
            player.parachute = true;
            player.ySpeed = 110;
        }else
            player.parachute = false;

        if (inputSystem.downPressed && player.onGround) {
            if (player.width < 40) {
                player.width++;
                player.x -= 0.5;
                player.height--;
                player.y += 1;
                player.checkCollision();
            }
        } else if (player.width > 30) {
            player.x += 0.5;
            player.y -= 0.5;
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