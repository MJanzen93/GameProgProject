package Game;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Game.GameObjects.GameObject;
import Game.GameObjects.CharacterObjects.Player;
import Game.GameObjects.Items.HealthItem;

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
}
