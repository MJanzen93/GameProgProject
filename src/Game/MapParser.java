package Game;

import Game.GameObjects.GameObject;
import Game.GameObjects.Items.HealthItem;
import Game.GameObjects.Player;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MapParser {

    public static List<List<GameObject>> getGameObjects(String filename) throws IOException {

        List<List<GameObject>> gameObjects = new ArrayList<>();

        List<GameObject> fixedObjects = new ArrayList<>();
        List<GameObject> movableObjects = new ArrayList<>();

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
        item.isFixed = true;
        return null;
    }

    private static GameObject createGameObject(String line){
        return new Player(0,0);
    }
}
