package Game;


import static java.lang.Thread.sleep;

import java.util.ArrayList;
import java.util.List;

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

    private boolean gameOver;

    // defines maximum frame rate
    private static final int FRAME_MINIMUM_MILLIS = 1;

    private boolean GameOver = false;

    public World() {
    }

    public void init() {
        //todo
        //List<GameObject> gameObjects = MapParser.getGameObjects("");

        //Backgound Musik player
        backgroundPlayer = new AudioPlayer();
        // backgroundPlayer.backGroundMusic(".\\src\\Game\\Sounds\\megalovania.wav",0.25);

        allObjects = new ArrayList<>();
        gameObjects = new ArrayList<>();
        fixedObjects = new ArrayList<>();
        bulletObjects = new ArrayList<>();
        backgroundObjects = new ArrayList<>();

        player = new Player(200, -2500);

        createWorld();
    }

    void createWorld() {

        List<List<GameObject>> list = MapParser.desert2();

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

        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                //print FPS
                diffMillis = 1;
                while(true){
                    System.out.println(1000 / diffMillis);
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        t.start();
    }

    long diffMillis = 1;

    void run() {
        long lastTick = System.currentTimeMillis();
        while (!gameOver) {
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


            //high ySpeed => get thinner
            if (Math.abs(player.ySpeed) > 500 && player.width > 20) {
                player.saveOldPosition();
                player.width--;
                player.x += 0.5;
                player.height++;
                player.y -= 0.5;
                player.checkCollision();
            } else if (player.width < 30) {
                player.saveOldPosition();
                player.width++;
                player.x -= 0.5;
                player.height--;
                player.y += 0.5;
                player.checkCollision();
            }

            //print FPS
           System.out.println(1000 / diffMillis);
            //System.out.println("X: "+ player.x + "Y: "+ player.y);


        }
    }


    void setGraphicSystem(WorldViewer wViewer) {
        this.wViewer = wViewer;
    }

    void setInputSystem(InputSystem inputSystem) {
        this.inputSystem = inputSystem;
    }

    //todo player.goLeft() ....
    public void processUserInput() {
        if (inputSystem.leftPressed) {
            player.goLeft();
        } else if (inputSystem.rightPressed) {
            player.goRight();
        } else if ((!inputSystem.leftPressed || !inputSystem.rightPressed) && player.xSpeed != 0) {
            player.stop();
        }

        if (inputSystem.upPressed && !player.jumping) {
            player.jump();
        }

        if (inputSystem.mousePressed && player.bulletCooldown <= 0 && !inputSystem.altPressed) {
            player.shootBullet(inputSystem);
            player.bulletCooldown = player.bulletCooldownfinal; //?? in player ??
        }

        if (player.missile > 0 && inputSystem.mousePressed && inputSystem.altPressed) {
            player.fireMissels(inputSystem);
        }

        if (inputSystem.downPressed && player.onGround) {
            if (player.width < 40) {
                player.width++;
                player.x -= 0.5;
                player.height--;
                player.y += 0.5;
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