package Game;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Game.GameObjects.Crate;
import Game.GameObjects.GameObject;
import Game.GameObjects.BackgroundObjects.BackgroundObject;

import Game.GameObjects.Bullets.Fire;

import Game.GameObjects.CharacterObjects.Player;

import Game.GameObjects.CharacterObjects.Enemies.FlyingEnemy;
import Game.GameObjects.CharacterObjects.Enemies.Mimic;
import Game.GameObjects.CharacterObjects.Enemies.SimpleBoss;
import Game.GameObjects.CharacterObjects.Enemies.SimpleEnemy;
import Game.GameObjects.CharacterObjects.Enemies.Speedy;
import Game.GameObjects.CharacterObjects.Enemies.Spikes;
import Game.GameObjects.CharacterObjects.Enemies.SpikesU;
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

    public static List<List<GameObject>> desert1(){
        List<List<GameObject>> list = new ArrayList<>();
        List<GameObject> fixedObjects = new ArrayList<>();
        List<GameObject> backgroundObjects = new ArrayList<>();
        List<GameObject> gameObjects = new ArrayList<>();
        list.add(fixedObjects);
        list.add(backgroundObjects);
        list.add(gameObjects);

        //Enemies
        FixedPlattform plat1Speedy = new FixedPlattform(4650,-10,1140,20,"");
        gameObjects.add(new Speedy(5760,-50,30,30,-1000,plat1Speedy));
        FixedPlattform plat1Speedy2 = new FixedPlattform(6050,940,890,20,"");
        gameObjects.add(new Speedy(6930,900,30,30,-1000,plat1Speedy2));
        FixedPlattform plat1Mimic = new FixedPlattform(7850,850,1660,20,"");
        gameObjects.add(new Mimic(9470,790,30,30,plat1Mimic, Color.YELLOW));
        
        
        //Items
        gameObjects.add(new HealthItem(3400,1190));
        gameObjects.add(new HealthItem(4905,640));
        
        FileReader fr;

        String result = "";
        try {
            fr = new FileReader(new File(".\\src\\Game\\map\\Desert1.tmx"));
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

                //HARDCODED****
                String[][] dataParts = new String[16][16];

                for (int j = 0; j < 16; j++) {
                    for (int j2 = 0; j2 < 16; j2++) {
                        dataParts[j][j2] = data[j*16+j2];
                    }
                }
                //***********

                int xi = meta[1].length()-1;
                int yi = meta[2].length()-1;
                int x = Integer.parseInt(meta[1].substring(3, xi));
                int y = Integer.parseInt(meta[2].substring(3, yi));

                for (int j = 0; j < dataParts.length; j++) {
                    for (int j2 = 0; j2 < dataParts.length; j2++) {
                        switch (dataParts[j][j2]) {

                            //EDIT
                            case "1":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\1.png", "TopLeft"));
                                break;
                            case "2":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\2.png", "Top"));
                                break;
                            case "3":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\3.png", "TopRight"));
                                break;
                            case "4":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\4.png", "Left"));
                                break;
                            case "5":
                                FixedPlattform f = new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\5.png");
                                f.hasCollision = false;
                                fixedObjects.add(f);
                                break;
                            case "6":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\6.png", "Right"));
                                break;
                            case "7":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\7.png", "Top"));
                                break;
                            case "8":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\8.png", "TopLeft"));
                                break;
                            case "9":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\9.png", "Bottom"));
                                break;
                            case "10":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\10.png", "TopRight"));
                                break;
                            case "11":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\11.png", "Top"));
                                break;
                            case "12":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\12.png", "BottomLeft"));
                                break;
                            case "13":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\13.png", "BottomRight"));
                                break;
                            case "14":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\14.png", "TopBottomLeft"));
                                break;
                            case "15":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\15.png", "TopBottom"));
                                break;
                            case "16":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\16.png", "TopBottomRight"));
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
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Tree.png"));
                                break;
                            case "30":
                                fixedObjects.add(new Mine(x*50 +j2*50, y*50 + j*50+45));
                                break;
                            case "31":
                                fixedObjects.add(new Coin(x*50 +j2*50, y*50 + j*50));
                                break;
                            case "32":
                                gameObjects.add(new FlyingEnemy(x*50 +j2*50, y*50 + j*50));
                                break;
                            case "33":
                                backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Stone.png"));
                                break;
                            case "34":
                                gameObjects.add(new Spikes(x*50 +j2*50, y*50 + j*50, 50, 50));
                                break;
                            case "35":
                            	backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\flag.png"));
                                break;
                            
                        }
                    }
                }
            }
        }
        return list;
    }

    public static List<List<GameObject>> desert3(){
        List<List<GameObject>> list = new ArrayList<>();
        List<GameObject> fixedObjects = new ArrayList<>();
        List<GameObject> backgroundObjects = new ArrayList<>();
        List<GameObject> gameObjects = new ArrayList<>();
        list.add(fixedObjects);
        list.add(backgroundObjects);
        list.add(gameObjects);
        
        
        gameObjects.add(new SimpleBoss(7766, -105, 100, 100));
        
        FileReader fr;

        String result = "";
        try {
            fr = new FileReader(new File(".\\src\\Game\\map\\Desert3.tmx"));

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

                //HARDCODED****
                String[][] dataParts = new String[16][16];

                for (int j = 0; j < 16; j++) {
                    for (int j2 = 0; j2 < 16; j2++) {
                        dataParts[j][j2] = data[j*16+j2];
                    }
                }
                //***********

                int xi = meta[1].length()-1;
                int yi = meta[2].length()-1;
                int x = Integer.parseInt(meta[1].substring(3, xi));
                int y = Integer.parseInt(meta[2].substring(3, yi));

                for (int j = 0; j < dataParts.length; j++) {
                    for (int j2 = 0; j2 < dataParts.length; j2++) {
                        switch (dataParts[j][j2]) {

                        //EDIT
                        case "1":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\1.png", "TopLeft"));
                            break;
                        case "2":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\2.png", "Top"));
                            break;
                        case "3":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\3.png", "TopRight"));
                            break;
                        case "4":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\4.png", "Left"));
                            break;
                        case "5":
                            FixedPlattform f = new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\5.png");
                            f.hasCollision = false;
                            fixedObjects.add(f);
                            break;
                        case "6":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\6.png", "Right"));
                            break;
                        case "7":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\7.png", "Top"));
                            break;
                        case "8":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\8.png"));
                            break;
                        case "9":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\9.png", "Bottom"));
                            break;
                        case "10":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\10.png"));
                            break;
                        case "11":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\11.png", "Top"));
                            break;
                        case "12":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\12.png", "BottomLeft"));
                            break;
                        case "13":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\13.png", "BottomRight"));
                            break;
                        case "14":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\14.png", "TopBottomLeft"));
                            break;
                        case "15":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\15.png", "TopBottom"));
                            break;
                        case "16":
                            fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\16.png", "TopBottomRight"));
                            break;
                        case "17":
                            gameObjects.add(new Crate(x*50 +j2*50, y*50 + j*50));
                            break;
                        case "18":
                            gameObjects.add(new SimpleEnemy(x*50 +j2*50, y*50 + j*50, 50, 50));
                            break;
                        case "19":
                            //gameObjects.add(new Player(x*50 +j2*50, y*50 + j*50));
                            gameObjects.add(new Player(774.199999999, y*50 + j*50));
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
                            fixedObjects.add(new Coin(x*50 +j2*50, y*50 + j*50));
                            break;
                        case "33":
                            gameObjects.add(new FlyingEnemy(x*50 +j2*50, y*50 + j*50));
                            break;
                        case "34":
                            gameObjects.add(new Spikes(x*50 +j2*50, y*50 + j*50, 50, 50));
                            break;
                        case "35":
                            fixedObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, 100, 100, ".\\src\\Game\\Textures\\flag.png"));
                            break;
                        case "36":
                            fixedObjects.add(new Fire(x*50 +j2*50, y*50 + j*50 - 32));
break;
                    }
                }
            }
        }
    }
return list;
    }
    
    public static List<List<GameObject>> desert2(){
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
            fr = new FileReader(new File(".\\src\\Game\\map\\desert2.tmx"));
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

                //HARDCODED****
                String[][] dataParts = new String[16][16];

                for (int j = 0; j < 16; j++) {
                    for (int j2 = 0; j2 < 16; j2++) {
                        dataParts[j][j2] = data[j*16+j2];
                    }
                }
                //***********

                int xi = meta[1].length()-1;
                int yi = meta[2].length()-1;
                int x = Integer.parseInt(meta[1].substring(3, xi));
                int y = Integer.parseInt(meta[2].substring(3, yi));

                for (int j = 0; j < dataParts.length; j++) {
                    for (int j2 = 0; j2 < dataParts.length; j2++) {
                        switch (dataParts[j][j2]) {

                            //EDIT
                            case "1":
                            	FixedPlattform fp14 = new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\14.png");
                            	fp14.destructible = true;
                            	fp14.hp = 5;
                            	fp14.maxHP = 5;
                                fixedObjects.add(fp14);
                                break;
                            case "2":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\14.png"));
                                break;
                            case "3":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\10.png", "TopRight"));
                                break;
                            case "4":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\5.png"));
                                break;
                            case "5":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\6.png", "Right"));
                                break;
                            case "6":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\8.png", "TopLeft"));
                                break;
                            case "7":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\9.png", "Bottom"));
                                break;
                            case "8":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\4.png", "Left"));
                                break;
                            case "9":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\2.png", "Top"));
                                break;
                            case "10":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\3.png", "TopRight"));
                                break;
                            case "11":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\16.png", "TopBottomRight"));
                                break;
                            case "12":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\1.png", "TopLeft"));
                                break;
                            case "13":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\11.png", "Top"));
                                break;
                            case "14":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 50, ".\\src\\Game\\Textures\\platformTiles\\Resized\\13.png", "BottomRight"));
                                break;
                            case "15":
                                fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\15.png", "TopBottom"));
                                break;
                            case "16":
                            	backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Grass (2).png"));
                                break;
                            case "17":
                            	FixedPlattform fp15 = new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\15.png", "TopBottom");
                            	fp15.destructible = true;
                            	fp15.hp = 5;
                            	fp15.maxHP = 5;
                                fixedObjects.add(fp15);
                                break;
                            case "18":
                            	FixedPlattform fp16 = new FixedPlattform(x*50 +j2*50, y*50 + j*50, 50, 36, ".\\src\\Game\\Textures\\platformTiles\\Resized\\16.png", "TopBottomRight");
                            	fp16.destructible = true;
                            	fp16.hp = 5;
                            	fp16.maxHP = 5;
                                fixedObjects.add(fp16);
                                break;
                            case "19":
                                gameObjects.add(new Player(x*50 +j2*50, y*50 + j*50));
                                break;
                            case "20":
                            	backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Tree.png"));
                                break;
                            case "21":
                            	backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\SignArrow.png"));
                                break;
                            case "22":
                            	backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Skeleton.png"));
                                break;
                            case "23":
                            	backgroundObjects.add(new BackgroundObject(x*50 +j2*50, y*50 + j*50, ".\\src\\Game\\Textures\\objects\\Stone.png"));
                                break;
                            case "24":
                            	fixedObjects.add(new Mine(x*50 +j2*50+20, y*50 + j*50+45));
                                break;
                            case "25":
                            	fixedObjects.add(new FixedPlattform(x*50 +j2*50, y*50 + j*50-50, 100, 100, ".\\\\src\\\\Game\\\\Textures\\\\flag.png"));
                                break;
                            case "26":
                                gameObjects.add(new Spikes(x*50 +j2*50, y*50 + j*50, 50, 50));
                                break;
                            case "27":
                                gameObjects.add(new FlyingEnemy(x*50 +j2*50, y*50 + j*50));
                                break;
                            case "28":
                                gameObjects.add(new SimpleEnemy(x*50 +j2*50, y*50 + j*50, 50, 50));
                                break;
                            case "29":
                                fixedObjects.add(new Fire(x*50 +j2*50, y*50 + j*50 - 32));
                            break;
                           
                        }
                    }
                }
            }
        }
        return list;
    }
}