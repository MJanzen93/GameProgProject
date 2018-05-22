package Game;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import Game.GameObjects.BackgroundObjects.BackgroundObject;
import Game.GameObjects.CharacterObjects.Enemies.SimpleEnemy;
import Game.GameObjects.Crate;
import Game.GameObjects.GameObject;
import Game.GameObjects.CharacterObjects.Player;
import Game.GameObjects.Items.Coin;
import Game.GameObjects.Items.HealthItem;
import Game.GameObjects.Platfrom.FixedPlattform;
import Game.GameObjects.Weapons.Mine;

public class MapParser {

    public static List<List<GameObject>> getGameObjects(String filename) throws IOException {

        List<List<GameObject>> allObjects = new ArrayList<>();

        List<GameObject> fixedObjects = new ArrayList<>();
        List<GameObject> gameObejcts = new ArrayList<>();
        List<GameObject> backgroundObjects = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(new File(filename)));
        String result = "";
        String line;

        //read file line by line
        while((line = br.readLine()) != null){
            result += line;
        }

        //process result...

        GameObject object = createGameObject("");

        if(object.isFixed && object.isSolid){
            fixedObjects.add(object);
        }

        HealthItem item = new HealthItem(50,50);
        return allObjects;
    }

    private static GameObject createGameObject(String line){
        return new Player(0,0);
    }

    public static List<List<GameObject>> getPlatforms(){
        List<List<GameObject>> list = new ArrayList<>();
        List<GameObject> fixedObjects = new ArrayList<>();
        List<GameObject> backgroundObjects = new ArrayList<>();
        List<GameObject> gameObjects = new ArrayList<>();
        list.add(fixedObjects);
        list.add(backgroundObjects);
        list.add(gameObjects);

        FileReader fr;

        String result = "";
        try {
            fr = new FileReader(new File(".\\src\\Game\\map\\unbenannt.tmx"));
            BufferedReader br = new BufferedReader(fr);

            String line;
            while((line = br.readLine()) != null) {
                result += line;
            }

        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String[] lines = result.split("(<chunk|</chunk>|</data>|</layer>|</map>)");

        for (int i = 1; i < lines.length; i++) {
            String[] part = lines[i].split(">");

            if(part.length >= 2) {
                String[] meta = part[0].split(" ");
                String[] data = part[1].split(",");

                String[][] dataParts = new String[16][16];

                for (int j = 0; j < 16; j++) {
                    for (int j2 = 0; j2 < 16; j2++) {
                        dataParts[j][j2] = data[j*16+j2];
                    }
                }

                int xi = meta[1].length()-1;
                int yi = meta[2].length()-1;
                int x = Integer.parseInt(meta[1].substring(3, xi));
                int y = Integer.parseInt(meta[2].substring(3, yi));

                for (int j = 0; j < dataParts.length; j++) {
                    for (int j2 = 0; j2 < dataParts.length; j2++) {
                        switch (dataParts[j][j2]) {
                            case "1":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\1.png"));
                                break;
                            case "2":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\2.png"));
                                break;
                            case "3":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\3.png"));
                                break;
                            case "4":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\4.png"));
                                break;
                            case "5":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\5.png"));
                                break;
                            case "6":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\6.png"));
                                break;
                            case "7":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\7.png"));
                                break;
                            case "8":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\8.png"));
                                break;
                            case "9":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\9.png"));
                                break;
                            case "10":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\10.png"));
                                break;
                            case "11":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\11.png"));
                                break;
                            case "12":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\12.png"));
                                break;
                            case "13":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\13.png"));
                                break;
                            case "14":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\14.png"));
                                break;
                            case "15":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\15.png"));
                                break;
                            case "16":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\16.png"));
                                break;
                            case "17":
                                gameObjects.add(new Crate(x*50 +j2*50, y*50 + j*50));
                                break;
                            case "18":
                                gameObjects.add(new SimpleEnemy(x*50 +j2*50, y*50 + j*50, 50, 50));
                                break;
                            case "19":
                                gameObjects.add(new Player(x*50 +j2*50, y*50 + j*50));
                                break;
                            case "20":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Bush (1).png"));
                                break;
                            case "21":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Bush (2).png"));
                                break;
                            case "22":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\SignArrow.png"));
                                break;
                            case "23":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Cactus (1).png"));
                                break;
                            case "24":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Cactus (2).png"));
                                break;
                            case "25":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Cactus (3).png"));
                                break;
                            case "26":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Grass (1).png"));
                                break;
                            case "27":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Grass (2).png"));
                                break;
                            case "28":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Skeleton.png"));
                                break;
                            case "29":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Stone.png"));
                                break;
                            case "30":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Tree.png"));
                                break;
                            case "31":
                                fixedObjects.add(new Mine(x*50 +j2*50, y*50 + j*50+45));
                                break;
                            case "32":
                                fixedObjects.add(new Coin(x*50 +j2*50, y*50 + j*50+45 - 22));
                                break;
                        }
                    }
                }
            }
        }

        return list;
    }
}
